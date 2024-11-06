package api.iTestListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import api.utilities.ExtentManager;

public class Listeners implements ITestListener{
	public ExtentReports reports;
	public ExtentTest test; 
	String testname;
	
	@Override
	public void onStart(ITestContext context) {
		 reports=ExtentManager.getExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		  testname = result.getName() ;
		  test=reports.createTest(testname);
		  test.log(Status.INFO, testname+"The test started executing");
		  
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 testname = result.getName() ;
		  test=reports.createTest(testname);
		  test.log(Status.PASS, testname+"The test pass successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testname = result.getName() ;
		  test=reports.createTest(testname);
		  test.log(Status.FAIL, testname+"The test was failed");
		  test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testname = result.getName() ;
		  test=reports.createTest(testname);
		  test.log(Status.SKIP, testname+"The test is skipped");
		  test.log(Status.INFO, result.getThrowable());
	}

	
	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

	
}
