package SupriyaBhosale.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SupriyaBhosale.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	 ExtentTest test;
	 ExtentReports exreport  = ExtentReporterNG.getReportObject();
	 ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // thread safe 
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = exreport.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  // unique thread id (errorvalidatiom test) > test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "test is passed ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
         // if we want to know the error message when test is failed for that getthrowable() is method 
		
		extentTest.get().fail(result.getThrowable());
		
		// if the test is failed then take screenshot and attached in to the report 
		
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		exreport.flush();
		
	}
	
	
	

}
