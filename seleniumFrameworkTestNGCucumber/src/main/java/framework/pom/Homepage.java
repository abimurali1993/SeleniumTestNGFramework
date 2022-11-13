package framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import framework.commons.BaseActions;

public class Homepage extends BaseActions {

	WebDriver driver;

	public Homepage(WebDriver driver) {
		super(driver);// to make sure the BaseActions class can use this driver
		this.driver = driver;
		PageFactory.initElements(driver, this);// for PageFactory

	}

	@FindBy(css = "div[class*='navigation-top-1'] a[data-gtm*='sign-up']")
	WebElement joinRewardsButton;
	
	@FindBy(xpath = "//span[@data-icon='user']")
	WebElement loginBtn;

	By heroBanner = By.className("hero-content");

	public void homePageURLVerification(String ExpectedURL) {

		String homePageURL = driver.getCurrentUrl();
		Assert.assertEquals(homePageURL, ExpectedURL);
	}

	public void verifyBanner() {
		waitForElementPresence(10, heroBanner);
	}

	public void clickOnJoinRewards() {
		normalClick(joinRewardsButton);
	}
	
	public void clickOnLoginBtn()
	{
		normalClick(loginBtn);
	}

}
