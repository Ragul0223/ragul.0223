package com.w2a.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.Testbase;
import com.w2a.utilities.Testutil;

@Test(dataProvider="getdata")
public class Addcustomerclass extends Testbase {
	public void addcustomer(Hashtable<String,String>data)throws InterruptedException {
        if(!data.get("runmode").equals("Y")) {
        	throw new SkipException("skipping the test case as run mode data set as no");
        }
		click("addCustBtn_XPATH");
		Thread.sleep(2000);
		type("firstname_XPATH",data.get("firstname"));
		type("lastname_XPATH",data.get("lastname"));
		type("postcode_XPATH",data.get("postcode"));
		click("addbtn_XPATH");
        Thread.sleep(2000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertext")));
		alert.accept();
		Thread.sleep(2000);
	}
	@DataProvider
	public Object[][] getdata() {
		String sheetName = "Addcustomerclass";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows-1][1];
        Hashtable<String,String> table=null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table= new Hashtable<String,String>();
			for (int colsNum = 0; colsNum < cols; colsNum++) {
				
				table.put(excel.getCellData(sheetName, colsNum, 1),excel.getCellData(sheetName, colsNum, rowNum));
				data[rowNum - 2][0] = table;

			}
		}
		return data;
	}
	}
	
