package framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v104.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.commons.BaseActions;

public class LoginPage extends BaseActions {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "react-form-field-loginID")
	WebElement userNameField;

	@FindBy(id = "react-form-field-password")
	WebElement passwordField;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement signInBtn;

	public void login(String email, String password) {
		sendText(userNameField, email);
		sendText(passwordField, password);
		normalClick(signInBtn);

	}

}
