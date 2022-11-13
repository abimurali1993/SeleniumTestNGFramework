package framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import framework.commons.BaseActions;

public class ProfilePage extends BaseActions {
	
	WebDriver driver;
	public ProfilePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//a[@data-name-pattern='Welcome NAME']")
	WebElement welcomeName;
	
	@FindBy(xpath = "//p[@class='personal-info__item-value mb-0']")
	WebElement profileEmail;
	
	
	
	public void verifyProfilePage(String URL)
	{
		waitForURLToBe(10, URL);
	}
	
	public void verifyUserName(String firstName)
	{
		getTextAndVerify(welcomeName, firstName);
	}
	
	public void verifyEmail(String email)
	{
		getTextAndVerify(profileEmail, email);
	}
	
	
	
	
	




}
