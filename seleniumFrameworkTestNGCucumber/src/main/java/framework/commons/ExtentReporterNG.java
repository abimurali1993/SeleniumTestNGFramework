package framework.commons;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG extends GenericFunctions {
	
	public ExtentReports extent;
	public ExtentReports getReporter() throws IOException
	{
		String path= System.getProperty("user.dir")+ propertyReader("ReportPath");
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("TestNG Hybrid Framework");
		reporter.config().setDocumentTitle("Test Automation Results");
		
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Author", "Abilash");
		extent.setSystemInfo("Application", "XXXX");
		extent.setSystemInfo("Framework", "TestNG Hybrid Framework v1.0");
		
		return extent;
	}

}
