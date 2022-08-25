package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.TestListenerAdapter;


import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.relevantcodes.extentreports.LogStatus;

import common.TestDriver;

public class CommonUtilities  {
	
	//create screen shots
	public static String getScreenshot(WebDriver driver) throws Exception {
		File destfile = null;
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		 File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 //File destfile = new File(("./reports/screenshots/screen.png"));
		 try{
			 System.out.println();
		 destfile = new File((System.getProperty("user.dir")+"/report/screenshots/"+formater.format(calendar.getTime())+".png"));
		 FileUtils.copyFile(srcfile,destfile);}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return destfile.getAbsolutePath();
	}
	
	public void onTestFailure(WebDriver driver, String Status) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//        String methodName = result.getName();
//        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                //String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File(("./reports/screenshots/"+formater.format(calendar.getTime())+".png"));
                FileUtils.copyFile(scrFile, destFile);
               // Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
//	}
    }
	
	//type in input field
	//accepts element, text to type, clearing the field before typing true/false 
	public static void sendKeys(WebElement element, String text, boolean clear){
		if(clear){
			element.clear();
		}
			element.sendKeys(text);
	}
	
	//clear input field
	public static void clear(WebElement element){
		element.clear();
	}
	
	//get element text
	public static String getText(WebElement element){
		return element.getText();
	}
	
	
	//click on element
	public static void click(WebElement element){
		element.click();
	}
	
	//check if element is present by size
	public static boolean isElementPresent(WebElement element){
		try {
			if(element.getSize() != null )
				return true;
			else
				return false;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
	//check Element present by By
	public static boolean isElementPresent(WebDriver driver,By by){
		try {
			driver.findElement(by); 
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	//wait for page to load
	public static void pageLoadTimeout(WebDriver driver,int seconds){
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	}
	
	public static void report(String Status, String Description, String ExpectedResult, String ActualResult, String Exception, String browser, boolean isScreenshotNeeded) throws Exception{
		LinkedHashMap<String, String> resultStep = new LinkedHashMap<String,String>();
		resultStep.put("Status", Status.toUpperCase());
		resultStep.put("Description", Description);
		resultStep.put("ExpectedResult", ExpectedResult);
		resultStep.put("ActualResult", ActualResult);
		resultStep.put("Exception", Exception);
		resultStep.put("Browser", browser);
		TestDriver.results.add(resultStep);
		if (isScreenshotNeeded) {
			//getScreenshot(TestDriver.driver, Status.toUpperCase());//removed status from signature as its not used in implementation
			getScreenshot(TestDriver.driver);
		}
		
		switch (Status.toUpperCase()) {
			case "PASS": TestDriver.test.log(LogStatus.PASS, Description); break;
			case "FAIL": TestDriver.test.log(LogStatus.FAIL, Description); break;
			case "WARNING": TestDriver.test.log(LogStatus.WARNING, Description); break;
			case "INFO": TestDriver.test.log(LogStatus.INFO, Description); break;
			case "ERROR": TestDriver.test.log(LogStatus.ERROR, Description); break;
			default: TestDriver.test.log(LogStatus.UNKNOWN, Description); break;
		}
		
	}
	
	public void recover(String Exception) throws Exception{
		LinkedHashMap<String, String> resultStep = new LinkedHashMap<String,String>();
		resultStep.put("Status", "ERROR");
		resultStep.put("Description", "An error occured during the execution");
		resultStep.put("Exception", Exception);
		TestDriver.results.add(resultStep);
		//getScreenshot(TestDriver.driver, "ERROR");
		getScreenshot(TestDriver.driver);
		TestDriver.test.log(LogStatus.ERROR, Exception);		
		//Assert.fail(Exception);
	}
	
	public static String clickAndSort(WebDriver driver, String columnName,String divId, boolean isAscendingOrder) throws Exception {
		String status;
		List<String> columnData = new ArrayList<String>();
		List<String> sortData = new ArrayList<String>();
		int colNum = findColumnNum(driver, columnName,divId);
		int lastPageNum = Integer
				.parseInt(driver.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the last page']"))
						.getAttribute("data-page"));
		WebElement currentPage=driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]"));
		String selectedPage=currentPage.getText();
		if (lastPageNum > 1) {
			if(Integer.parseInt(selectedPage)!=1){
				clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the first page']",
						"//div[@id='"+divId+"']//a[@title='Go to the first page' and contains(@class,'disabled')]");
			}
		}
		WebElement element = driver.findElement(By.xpath("//div[@id='"+divId+"']//a[contains(text(),'" + columnName + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
		for (int j = 1; j <= lastPageNum; j++) {
			int RowNum = driver.findElements(By.xpath("//div[@id='"+divId+"']//table/tbody/tr")).size();
			for (int i = 1; i <= RowNum; i++) {
				if (isElementPresent(driver,
						By.xpath("//div[@id='"+divId+"']//table/tbody/tr[" + i + "]/td[" + colNum + "]"))) {
					WebElement column = driver.findElement(
							By.xpath("//div[@id='"+divId+"']//table/tbody/tr[" + i + "]/td[" + colNum + "]"));
					String columnValues = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].innerHTML;", column);
					columnData.add(columnValues);
				}
			}
			if (j < lastPageNum) {
				WebElement next = driver.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the next page']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
			}
		}
		sortData.addAll(columnData);
		if (isAscendingOrder) {
			Collections.sort(sortData);
		} else {
			Collections.sort(sortData, Collections.reverseOrder());
		}
		if (columnData.equals(sortData))
			status = "pass";
		else
			status = "fail";
		return status;
	}

	public static String pagination(WebDriver driver,String divId) throws InterruptedException {
		String status = "Pass";
		WebElement currentPage=driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]"));
		String selectedPage=currentPage.getText();
		int lastPageNum = Integer
				.parseInt(driver.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the last page']"))
						.getAttribute("data-page"));
		if (lastPageNum > 1) {
			if(Integer.parseInt(selectedPage)!=1){
				clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the first page']",
						"//div[@id='"+divId+"']//a[@title='Go to the first page' and contains(@class,'disabled')]");
			}
			for(int i=2;i<=lastPageNum;i++){
				WebElement Page = driver.findElement(By.xpath("//div[@id='"+divId+"']//a[contains(text(),'"+i+"')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Page);
				if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText().equals(String.valueOf(i))){
					status = "Fail";
				}
			}
			clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the first page']",
					"//div[@id='"+divId+"']//a[@title='Go to the first page' and contains(@class,'disabled')]");
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText().equals("1")
					|| !driver
							.findElement(
									By.xpath("//div[@id='"+divId+"']//a[@title='Go to the previous page' and contains(@class,'disabled')]"))
							.isDisplayed()){
				status = "Fail";
			}
			clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the last page']",
					"//div[@id='"+divId+"']//a[@title='Go to the last page' and contains(@class,'disabled')]");
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText().equals(String.valueOf(lastPageNum))
					|| !driver
							.findElement(By
									.xpath("//div[@id='"+divId+"']//a[@title='Go to the next page' and contains(@class,'disabled')]"))
							.isDisplayed()){
				status = "Fail";
			}
			WebElement previousPage = driver
					.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the previous page']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", previousPage);
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText()
					.equals(String.valueOf(lastPageNum - 1))){
				status = "Fail";
			}
			clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the next page']",
					"//div[@id='"+divId+"']//a[@title='Go to the last page' and contains(@class,'disabled')]");
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText()
					.equals(String.valueOf(lastPageNum))){
				status = "Fail";
			}
		}
		return status;
	}
	
	public static void clickAndWait(WebDriver driver,String elementXpath,String waitCondionXpath){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element=driver.findElement(By.xpath(elementXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
         wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(waitCondionXpath))));
	}

	public static int findColumnNum(WebDriver driver, String columnName,String divId) {
		int colSize = driver.findElements(By.xpath("//div[@id='"+divId+"']//table/thead/tr/th")).size();
		int i;
		for (i = 1; i <= colSize; i++) {
			String colName = driver.findElement(By.xpath("//div[@id='"+divId+"']//table/thead/tr/th[" + i + "]")).getText();
			if (colName.equalsIgnoreCase(columnName)) {
				break;
			}
		}
		return i;
	}
	public static boolean assertValues(WebDriver driver, LinkedHashMap<String, String> hm) throws InterruptedException {

		List<WebElement> totalheaders = driver.findElements(By.xpath(hm.get("Tableid") + "/th"));
		boolean b = false;
		for (int i = 0; i <= totalheaders.size() - 1; i++) {
			Thread.sleep(2000);
			for (Entry<String, String> entry : hm.entrySet()) {
				if (totalheaders.get(i).getText().equalsIgnoreCase(entry.getKey())) {
					int totalrows = driver.findElements(By.xpath(hm.get("Tableid") + "/tr")).size();
					for (int j = 1; j <= totalrows - 1; j++) {
						if (driver.findElement(By.xpath(hm.get("Tableid") + "/tr[" + j + "]/td[" + (i + 1) + "]"))
								.getText().equalsIgnoreCase((String) entry.getValue())) {
						
							b = true;

						} else {
							b = false;
						}

					}
				}
			}
		}

		return b;
	}
	
	public static void enterInput(String value, WebElement field) {
		field.click();
		field.sendKeys(Keys.CONTROL, "a");
		// field.sendKeys(Keys.DELETE);
		if (value.equals("")) {
			field.sendKeys(Keys.DELETE);
		} else {
			field.sendKeys(value);
		}
		field.sendKeys(Keys.TAB);

		return;
	}
	
	public static void uploadFinalDocument(String filePath) throws AWTException, InterruptedException {
		StringSelection path = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
		Robot robot = new Robot();
        robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	public static WebElement isElementClickable( WebDriver driver,By ele) {

		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(ele)));
		WebElement Clickelement = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ele)));
		return Clickelement;

	}
	
	public static boolean isPresenceOfElementLocated( WebDriver driver,By ele) {

		WebDriverWait wait = new WebDriverWait(driver, 180);
		boolean Clickelement = wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(ele), "Sign In")); // presenceOfElementLocated(ele));  //elementToBeClickable(ele));
		return Clickelement;

	}
	
	public static void RefreshPage(WebDriver driver) throws IOException, InterruptedException
	{
		int timeOut = 0;
		while(!(driver.getTitle().toString().contains("Login - Form Page | Singapore Tourism Board")))
		{
			driver.navigate().refresh();
			Thread.sleep(1000);
			timeOut++;
			if(timeOut>10)
			{
				break;
			}
		}
	}
	
	public static WebElement isElementVisible( WebDriver driver,By ele) {

		WebDriverWait wait = new WebDriverWait(driver, 180);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		return element;

	}
	public static void  deleteCookies(WebDriver driver) throws AWTException, InterruptedException
	{
		Thread.sleep(1000);
		Robot robot = new Robot();
		System.out.println("inside deletecookies");
		//robot.isAutoWaitForIdle();
		
		/*Keyboard keyboard = ((RemoteWebDriver) driver).getKeyboard();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_DELETE);
        
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SHIFT);		
		robot.keyRelease(KeyEvent.VK_DELETE);
		
		
		keyboard.pressKey(Keys.CONTROL);
		keyboard.pressKey(Keys.SHIFT);
	    keyboard.pressKey(Keys.DELETE);
	    
	    keyboard.releaseKey(Keys.DELETE);
	    keyboard.releaseKey(Keys.SHIFT);
	    keyboard.releaseKey(Keys.CONTROL);
	    
		Thread.sleep(2000);
		
		System.out.println("after control shift delete press");
		
		keyboard.pressKey(Keys.CONTROL);
		keyboard.pressKey(Keys.TAB);
		keyboard.releaseKey(Keys.TAB);
		keyboard.releaseKey(Keys.CONTROL);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);*/
		
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();
		
		/*Thread.sleep(2000);
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    
	    driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();
		*/
	    
	    
	    
	    System.out.println("done clering history");
	}
	
	public static void clickUsingJavaScriptExecutor( WebDriver driver,By ele) {

		WebElement element = CommonUtilities.isElementClickable(driver,ele);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);

	}
	public static void pageScrollToBottom(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static boolean retryingFindClick(WebDriver driver,By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
} 
}



