package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
//import org.testng.ITestContext;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


import cucumber.api.Scenario;
import cucumber.api.java.Before;
import utilities.BrowserUtilities;
import utilities.CommonUtilities;


public class TestDriver extends CommonUtilities {

	public static LinkedHashMap<String, String> parameters;
	public static LinkedList<LinkedHashMap<String, String>> results = new LinkedList<LinkedHashMap<String, String>>();
	public static WebDriver driver;
	public static ExtentReports extent;
	static Calendar calendar = Calendar.getInstance();
    static SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	
	static String Path = "./report/htmlreports/"+formater.format(calendar.getTime())+"ExtentReport.html";
	
	public static ExtentTest test;
	
	//@BeforeMethod
	public static void InitializeTestIteration() {
		
		driver = BrowserUtilities.getBrowser("Chrome");
		Capabilities cap = ((RemoteWebDriver) TestDriver.driver).getCapabilities();
		//System.out.println(Path);	
		/*
		 * extent = new ExtentReports(Path, true); File config = new
		 * File("./extentreports_config.xml"); extent.loadConfig(config);
		 * //parameters.put("browser_version", cap.getBrowserName().toString() + " " +
		 * cap.getVersion().toString()); test = extent.startTest("Test Report", "[" +
		 * "Chrome" + "] " + "Test Report");
		 */
		/*test.assignAuthor("Selvaraj Karuppiah");
		test.assignCategory("Smoke");*/
		System.out.println("before");
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
	}

	@DataProvider(name = "data-provider")
	public static Object[][] dataProvider(ITestContext context) {
		String[] browsersArray = context.getCurrentXmlTest().getParameter("browsers").split(",");
		Object[][] obj = new Object[browsersArray.length][1];
		for (int i = 0; i < browsersArray.length; i++) {
			obj[i][0] = browsersArray[i];
		}
		System.out.println("data provider op");	
		return obj;
	}		
	
	//@AfterMethod
	public static void tearDown() {
		

		if (driver != null)
			//driver.close();
			driver.quit();
		//extent.endTest(test);
		System.out.println("after");
	}

	//@AfterSuite
	public static void flush() {
		//extent.flush();
		//extent.close();
		System.out.println("after suite op");
	}
	
	
	

}

