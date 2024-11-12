package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.w2a.base.Testbase;

public class BankManagerLogintest extends Testbase {
	
	@Test
	public void bankmanagerlogintest() throws InterruptedException, IOException {


	verifyEquals("abc","xyz");
		
	    log.debug("inside login test");
		click("btmbutn_XPATH");
		
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustBtn_XPATH"))),"login sucess");
		
		Thread.sleep(4000);
		
    
	}

	
}
