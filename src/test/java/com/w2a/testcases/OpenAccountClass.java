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
public class OpenAccountClass extends Testbase {
	public void openAccount(Hashtable<String,String>data) throws InterruptedException {
		
		click("openaccountbtn_XPATH");
		Thread.sleep(2000);
		select("customerbtn_XPATH",data.get("customer"));
		select("currencybtn_XPATH",data.get("currency"));
        click("processbtn_XPATH");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(4000);
		alert.accept();
	

		

	}@DataProvider
	public Object[][] getdata() {
		String sheetName = "OpenAccountClass";
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
}}
