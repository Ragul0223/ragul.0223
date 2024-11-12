package com.w2a.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.Testbase;
import com.w2a.utilities.Testutil;

public class CustomListerners extends Testbase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());

		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
		rep.endTest(test);
		rep.flush();
        //runmode
     
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			Testutil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+" Failed with exception: "+result.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(Testutil.screenshotName));

		Reporter.log("Click to see screenshot");
		Reporter.log("<a target=\"_blank\"href="+Testutil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\"href="+Testutil.screenshotName+"><img src="+Testutil.screenshotName+" height=200 width=200></img></a>");
		rep.endTest(test);
		rep.flush();
		}
	
		
	
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		
		test.log(LogStatus.SKIP, result.getTestName()+"skipping by the exception");
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
