package stepdef;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.TestDriver;
import utilities.CommonUtilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends TestDriver {
	
	//private static WebDriver driver;
	//static TestDriver testdriver = PageFactory.initElements(driver, TestDriver.class);

	@Before
	public static void beforeClass() throws Exception {
		
		TestDriver.InitializeTestIteration();
	}

	/*@DataProvider(name = "data-provider")
	public static Object[][] dataProvider(ITestContext context) {
		String[] browsersArray = context.getCurrentXmlTest().getParameter("browsers").split(",");
		Object[][] obj = new Object[browsersArray.length][1];
		for (int i = 0; i < browsersArray.length; i++) {
			obj[i][0] = browsersArray[i];
		}
		System.out.println("data provider op");	
		return obj;
	}*/

	@After
	public static void afterClass(Scenario scenario) throws Exception {
		
		
		
		if(scenario.isFailed())	
		{
			//CommonUtilities.getScreenshot(driver);
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			
		}
		if(scenario.getStatus().equalsIgnoreCase("passed"))
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		
		TestDriver.tearDown();
		
		TestDriver.flush();
	}
		

}
