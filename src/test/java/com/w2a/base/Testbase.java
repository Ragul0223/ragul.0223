package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Testutil;

public class Testbase {

	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports rep = ExtentManager.getinstance();
	public static ExtentTest test;
	
	@BeforeSuite
	
	public void setup(){
		
	
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("configure file loaded !");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			log.debug("or file loaded !");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(config.getProperty("browser").equals("firefox")) {
			driver=new FirefoxDriver();
		}else if (config.getProperty("browser").equals("chrome")){
			
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executebels\\chromedriver.exe");
		
		 driver = new ChromeDriver();
		 log.debug("chrome launched !");
		}else if (config.getProperty("browser").equals("ie")){
			
		
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executebels\\IEDriverServer.exe");
		
		driver = new InternetExplorerDriver();
		}
		
		driver.get(config.getProperty("testurl"));
		
		 log.debug("Navigated to: "+config.getProperty("testurl"));
		driver.manage().window().maximize();
		
	//	driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit wait")),Timeout.millis(2000));		
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
	}
	
	
	public void click(String locator) {
		if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			}
		test.log(LogStatus.INFO,"clicking on:"+ locator);
		
	}
    public void type(String locator, String values) {
    	if(locator.endsWith("_CSS")) {
    	driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(values);
    	}else if(locator.endsWith("_XPATH")) {
        	driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(values);
    	}
    	test.log(LogStatus.INFO,"Typing in :"+ locator+" entered values :"+values);
		
    }
    static WebElement dropdown;
    public void select(String locator,String values) {
    	if(locator.endsWith("_CSS")) {
        	dropdown= driver.findElement(By.cssSelector(OR.getProperty(locator)));
        	}else if(locator.endsWith("_XPATH")) {
            dropdown= driver.findElement(By.xpath(OR.getProperty(locator)));
        	}
    	    Select select = new Select(dropdown);
    	    select.selectByVisibleText(values);
        	test.log(LogStatus.INFO,"Selecting by values :"+ locator+"values as :"+values);
    	
    	
    	
    }
	
	
	public boolean isElementPresent(By by) {

		try {
       //   driver.findElement(By);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}
	
	public static void verifyEquals(String expected,String actual) throws IOException {
		try {
			Assert.assertEquals(expected,actual);
			
		}catch(Throwable t) {
		
		Testutil.captureScreenshot();
		//report ng
		Reporter.log("<br>"+"verification failure : "+t.getMessage()+"<br>");
		Reporter.log("<a target=\"_blank\"href="+Testutil.screenshotName+"><img src="+Testutil.screenshotName+" height=200 width=200></img></a>");
		Reporter.log("<br>");
	    Reporter.log("<br>");
		// extend reports
	    test.log(LogStatus.FAIL, "verification Failed with exception: "+t.getMessage());
		test.log(LogStatus.FAIL,test.addScreenCapture(Testutil.screenshotName));

	    
	    
	    
		}
		
	}
	
	@AfterSuite  
	public void teardown() {
		
		driver.quit();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
