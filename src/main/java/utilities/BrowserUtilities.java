package utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtilities {
	
	private static WebDriver driver;
//	private static String browserName;
	private static File path = new File("src/main/resources/drivers");
	
	//derive the browser needed
	public static WebDriver getBrowser(String browserName){
		if(browserName.equalsIgnoreCase("FF")){
			driver = getFireoxBrowser();
		} else if(browserName.equalsIgnoreCase("IE")){
			driver = getInternetExplorerDriver();
		} else if(browserName.equalsIgnoreCase("Chrome")){
			driver = getChromeDriver();
		} else if(browserName.equalsIgnoreCase("HeadlessChrome")){
			driver = getHeadlessChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	/*load FF browser*/
	public static WebDriver getFireoxBrowser(){
		new DesiredCapabilities();
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.ALL);
		logs.enable(LogType.CLIENT, Level.ALL);
		logs.enable(LogType.DRIVER, Level.INFO);
		logs.enable(LogType.PERFORMANCE, Level.ALL);
		logs.enable(LogType.PROFILER, Level.ALL);
		logs.enable(LogType.SERVER, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		
		//C:\Program Files\Mozilla Firefox
		//System.setProperty("webdriver.firefox.driver", "C:\\Program Files\\Mozilla Firefox\\IEDriverServer.exe");
		
		driver = new FirefoxDriver(capabilities);
		return driver;
	}
	
	//load IE browser
	public static WebDriver getInternetExplorerDriver(){
		new DesiredCapabilities();
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.ALL);
		logs.enable(LogType.CLIENT, Level.ALL);
		logs.enable(LogType.DRIVER, Level.INFO);
		logs.enable(LogType.PERFORMANCE, Level.ALL);
		logs.enable(LogType.PROFILER, Level.ALL);
		logs.enable(LogType.SERVER, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		
		capabilities.setJavascriptEnabled(true);
		System.setProperty("webdriver.ie.driver", path.getAbsolutePath() + "\\IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
		return driver;
	}
	
	//load Chrome browser
	public static WebDriver getChromeDriver(){
		
				
		new DesiredCapabilities();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//capabilities.setCapability("proxy", proxy); 
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--no-sandbox");
		//options.addArguments("start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY,options);
		
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.ALL);
		logs.enable(LogType.CLIENT, Level.ALL);
		logs.enable(LogType.DRIVER, Level.INFO);
		logs.enable(LogType.PERFORMANCE, Level.ALL);
		logs.enable(LogType.PROFILER, Level.ALL);
		logs.enable(LogType.SERVER, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		
		//System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
		//System.setProperty("webdriver.chrome.driver", path.getAbsolutePath() + "\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(capabilities);
		return driver;
	}
	
	public static WebDriver getHeadlessChromeDriver(){
		
		ChromeOptions chromeOptions = new ChromeOptions();  
	    //System.setProperty("webdriver.chrome.driver", pathofchromebrowser"); 
		//System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
		System.setProperty("webdriver.chrome.driver", path.getAbsolutePath() + "\\chromedriver.exe");
	    chromeOptions.addArguments("--headless");  
	    chromeOptions.addArguments("--disable-gpu");  
	    chromeOptions.addArguments("--no-sandbox");
	    WebDriver driver = new ChromeDriver(chromeOptions);
		
		return driver;
		
	}

}
