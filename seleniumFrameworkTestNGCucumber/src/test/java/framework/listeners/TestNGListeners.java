package framework.listeners;

import java.io.IOException;

import javax.naming.spi.ResolveResult;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework.commons.ExtentReporterNG;
import framework.commons.GenericFunctions;

public class TestNGListeners extends GenericFunctions implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent;
	ExtentReporterNG extentReports= new ExtentReporterNG();
	WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
		
		try {
			extent=extentReports.getReporter();
			test=extent.createTest(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test case Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		
		try {
			driver= (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String filePath=null;
		
		try {
			filePath= getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
