package framework.commons;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BaseActions {

	WebDriver driver;

	public BaseActions(WebDriver driver) {
		this.driver = driver;
	}

	// Wait Methods
	/**
	 * 
	 * @param driver
	 * @param timeOut
	 * @param locator
	 */
	public void waitForElementPresence(int timeOut, By locator)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementPresence(int timeOut, WebElement element)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementInvisible(int timeOut, By locator)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForelementToBeClickable(int timeOut, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForURLToBe(int timeout, String URL)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlToBe(URL));
	}
	
	public void waitForAllElementPresence(int timeout, By locator)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	// Action Methods

	public void normalClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();

	}

	public void sendText(WebElement element, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(value);

	}

	public void moveToAndClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions act = new Actions(driver);
		act.moveToElement(element).click().build().perform();
	}

	public void selectByVisibleText(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(text);
	}

	public void selectByValue(WebElement element, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}

	public void selectByIndex(WebElement element, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}

	public void getTextAndVerify(WebElement element, String ExpectedText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		String actualText = element.getText();
		SoftAssert assert1 = new SoftAssert();
		assert1.assertEquals(actualText, ExpectedText);
	}
	
	public void getTextAndVerifyContains(WebElement element, String ExpectedText) {
		Boolean flag;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		String actualText = element.getText();
		SoftAssert assert1 = new SoftAssert();
		if(ExpectedText.contains(actualText))
			flag=true;
		else
			flag=false;
		
		assert1.assertTrue(flag);
			
	}
	
	public void softAssertTrue(WebElement element, String text)
	{
		SoftAssert asserting= new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		asserting.assertTrue(element.getText().contains(text));
	}

}
