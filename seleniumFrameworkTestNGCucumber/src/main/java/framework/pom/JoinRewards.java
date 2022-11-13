package framework.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import framework.commons.BaseActions;

public class JoinRewards extends BaseActions {

	WebDriver driver;

	public JoinRewards(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="email_id")
	WebElement emailField;
	
	@FindBy(id="first-name")
	WebElement firstNameField;
	
	@FindBy(id="last-name")
	WebElement lastNameField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(id="lineOne")
	WebElement addressField;
	
	@FindBy(className = "pac-item")
	List<WebElement> addressOptions;
	
	@FindBy(className = "abt-icon-calendar")
	WebElement calandarIcon;
	
	@FindBy(className = "month-item-name")
	WebElement monthField;
	
	@FindBy(className = "month-item-year")
	WebElement yearField;
	
	@FindBy(className = "day-item")
	List<WebElement> days;
	
	@FindBy(id = "button-id")
	WebElement signupBtn;
	
	
	By googleAddress= By.className("pac-item");

	public void normalRegistration(String email, String firstName, String lastName, String password, String address, String month, String year, String dateofBirth) {

		sendText(emailField, email);
		sendText(firstNameField, firstName);
		sendText(lastNameField, lastName);
		sendText(passwordField, password);
		sendText(addressField, address);
		normalClick(passwordField);
		normalClick(addressField);
		waitForElementPresence(10, googleAddress);
		moveToAndClick(addressOptions.get(0));
		waitForelementToBeClickable(10, calandarIcon);
		normalClick(calandarIcon);
		
		selectByVisibleText(monthField, month);
		selectByValue(yearField, year);

		days.stream().filter(day -> day.getText().equals(dateofBirth)).limit(1).forEach(day -> day.click());
		normalClick(signupBtn);


	}

}
