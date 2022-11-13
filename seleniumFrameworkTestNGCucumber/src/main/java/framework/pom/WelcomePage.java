package framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import framework.commons.BaseActions;

public class WelcomePage extends BaseActions {

	WebDriver driver;

	public WelcomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "shadowedTitle")
	WebElement welcomeMessage;

	public void verifyWelcomeMessage() {
		getTextAndVerify(welcomeMessage, "Welcome to Similac");
	}

}
