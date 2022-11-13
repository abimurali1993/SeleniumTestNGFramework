package framework.tests;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZeroScript {
	
	public static void main(String[] args) throws InterruptedException
	{
		
		String email="abilash.c11@getnada.com";
		String firstName="Abilash";
		String lastName="Cheruvathur";
		String password="Test@1234";
		String address="1234 New";
		String yearofBirth="2023";
		String monthofBirth="October";
		String dateofBirth="18";
		String productSearch="infant";
		String productToAdd="Advance";
		
		ChromeOptions options= new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.similac.com/");
		
		String homePageURL=driver.getCurrentUrl();
		Assert.assertEquals(homePageURL, "https://www.similac.com/home.html");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hero-content")));
		
		driver.findElement(By.cssSelector("div[class*='navigation-top-1'] a[data-gtm*='sign-up']")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("pie")));

		driver.findElement(By.id("email_id")).sendKeys(email);
		driver.findElement(By.id("first-name")).sendKeys(firstName);
		driver.findElement(By.id("last-name")).sendKeys(lastName);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.id("lineOne")).sendKeys(address);
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("lineOne")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item")));
		List<WebElement> addressOptions= driver.findElements(By.className("pac-item"));
		Actions act= new Actions(driver);
		act.moveToElement(addressOptions.get(1)).click().build().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.className("abt-icon-calendar")));
		driver.findElement(By.className("abt-icon-calendar")).click();
		WebElement month= driver.findElement(By.className("month-item-name"));
		wait.until(ExpectedConditions.elementToBeClickable(month));
		Select selectMonth= new Select(month);
		selectMonth.selectByVisibleText(monthofBirth);
		WebElement year= driver.findElement(By.className("month-item-year"));
		Select selectYear= new Select(year);
		selectYear.selectByValue(yearofBirth);
		List<WebElement> days=driver.findElements(By.className("day-item"));
		days.stream().filter(day->day.getText().equals(dateofBirth)).limit(1).forEach(day->day.click());
		WebElement signupBtn=driver.findElement(By.id("button-id"));
		wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("pie")));
		
		WebElement welcomeMessage= driver.findElement(By.className("shadowedTitle"));
		wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
		Assert.assertEquals(welcomeMessage.getText(), "Welcome to Similac");
		
		driver.findElement(By.xpath("//a[@data-fun='signOut']")).click();
//		
		//driver.navigate().to("https://www.similac.com/rewards/admin/login.html");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-form-field-loginID")));
		driver.findElement(By.id("react-form-field-loginID")).sendKeys(email);
		driver.findElement(By.id("react-form-field-password")).sendKeys(password);
		WebElement loginButton=driver.findElement(By.xpath("//button[@type='submit']"));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("pie")));
		wait.until(ExpectedConditions.urlToBe("https://www.similac.com/account/profile.html"));
		WebElement loginName=driver.findElement(By.xpath("//a[@data-name-pattern='Welcome NAME']"));
		wait.until(ExpectedConditions.visibilityOf(loginName));
		Boolean flag;
		if(loginName.getText().contains(firstName))
		{
			flag=true;
		}
		else
			flag=false;
		
		Assert.assertTrue(flag);
		
		WebElement accountEmail=driver.findElement(By.xpath("//p[@class='personal-info__item-value mb-0']"));
		wait.until(ExpectedConditions.visibilityOf(accountEmail));
		Assert.assertEquals(accountEmail.getText(), email);
		
		driver.findElement(By.id("navbarDropdown0")).click();
		
		driver.findElement(By.id("react-form-field-search")).sendKeys(productSearch);
		driver.findElement(By.cssSelector(".clickable")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("products__card--figure")));
		
		do
		{
		List<WebElement> products= driver.findElements(By.className("product-card"));
		products.stream().filter(temp-> temp.findElement(By.className("figure-caption")).getText().contains(productToAdd))
		.filter(temp->temp.findElements(By.className("btn-primary")).size()>0)
		.forEach(temp->temp.findElement(By.className("btn-primary")).click());
		
		driver.findElement(By.cssSelector("li[class*='next'] a")).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("products__card--figure")));
		}while (driver.findElement(By.cssSelector("li[class*='next'] a")).getAttribute("aria-disabled").equals("false"));
		
		driver.findElement(By.id("mini-cart")).click();
		
		WebElement miniCart=driver.findElement(By.className("minicart-products"));
		wait.until(ExpectedConditions.visibilityOf(miniCart));
		Assert.assertTrue(miniCart.findElement(By.className("product-title")).getText().contains(productToAdd));
		
		
		
		
		
		
	}

}
