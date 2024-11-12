package com.w2a.utilities;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.Testbase;
import java.io.File;
public class Testutil extends Testbase {

	public static String screenshotPath;
	public static String screenshotName;



	public static void captureScreenshot() throws IOException {

		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}
	public static boolean isTestRunnable(String testname,ExcelReader excel) {
		String sheetname = "test_suite";
		int rows=excel.getRowCount(sheetname);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String testcase = excel.getCellData(sheetname, "TCID", rNum);
			if(testcase.equalsIgnoreCase(testname)) {
				String runmode=excel.getCellData(sheetname,"Runmode", rNum);
				if(runmode.equalsIgnoreCase("Y")) {
					return true;
				}else
					return false;
			}
			
		}
		
		return false;
	}

   
}






