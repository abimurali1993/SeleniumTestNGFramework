package framework.commons;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	
	GenericFunctions generic= new GenericFunctions();
	public static WebDriver driver;
	public  WebDriver invokeBrowser() throws IOException
	{
		
		String browserName=generic.propertyReader("Browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options= new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		return driver;
	}
	@BeforeMethod(alwaysRun = true)
	public  WebDriver launchApplication() throws IOException
	{
		invokeBrowser();
		String environment=generic.propertyReader("Environment");
		if (environment.equalsIgnoreCase("PROD"))
		{
			driver.get(generic.propertyReader("PROD_URL"));
			System.out.println(generic.propertyReader("PROD_URL"));
		}
		else if(environment.equalsIgnoreCase("STAGE"))
		{
			driver.get(generic.propertyReader("STAGE_URL"));
		}
		else if(environment.equalsIgnoreCase("QA"))
		{
			driver.get(generic.propertyReader("QA_URL"));
		}
		
		else if(environment.equalsIgnoreCase("DEV"))
		{
			driver.get(generic.propertyReader("DEV_URL"));
		}
		return driver;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()
	{
		driver.close();
	}
	


}
