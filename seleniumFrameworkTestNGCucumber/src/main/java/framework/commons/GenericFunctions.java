package framework.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenericFunctions {
	
	
	
	public String propertyReader(String key) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(new File("src//main/java//framework//resources//GlobalParameter.properties"));
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}


	public String getScreenshot(String testName, WebDriver driver) throws IOException
	{
		TakesScreenshot screenshotObject=(TakesScreenshot)driver;
		File src=screenshotObject.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir") +"//reports//" +testName +".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") +"//reports//" +testName +".png";
	}
	
}
