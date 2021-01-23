package com.saudhing.cucumber.qedge.steps;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saudhing.ExtentListeners.ExtentTestManager;
import com.saudhing.utilities.DriverFactory;
import com.saudhing.utilities.DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSteps {
	
	//static - close browser after every scenario
	//private static WebDriver driver;
	protected static WebDriver driver;
	public Logger log = Logger.getLogger(BaseSteps.class);
	public boolean grid = false;
	
	public void setUpFramework() {
		
		//configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		//DriverFactory.setConfigPropertyFile(System.getProperty("user.dir") + "//src//test//resources//properties/Config.properties");

	}

	public void logInfo(String message) {

		ExtentTestManager.testReport.get().info(message);

	}

	public void configureLogging() {
		
		String log4jConfigFile = System.getProperty("user.dir") + "//src//test//resources//properties/log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);

	}
	
	public void openBrowser(String browser) {
		
		//decide local or grid execution
		if (System.getenv("ExecutionType") != null && System.getenv("ExecutionType").equals("Grid")) {
			grid = true;
		}

		DriverFactory.setRemote(grid);

		if (DriverFactory.isRemote()) {

			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
				//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				driver = new RemoteWebDriver(new URL("http://172.20.110.97:4444/wd/hub"), cap);
				System.out.println("Starting the session on Grid !!!");
				log.info("Starting the session on Grid !!!");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			if (browser.equals("chrome") && DriverManager.getDriver() == null) {
				System.out.println("Launching : " + browser);
				//System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				System.out.println("Chrome browser launched !!!");
				log.info("Chrome browser launched !!!");
			} else if (browser.equals("firefox") && DriverManager.getDriver() == null) {
				System.out.println("Launching : " + browser);
				//System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				System.out.println("Firefox browser launched !!!");
				log.info("Firefox browser launched !!!");
			}

		}
		
		DriverManager.setWebDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//setDefaultUserName(Config.getProperty("defaultUserName"));
		//setDefaultPassword(Config.getProperty("defaultPassword"));

	} 

	public void quit() {

		DriverManager.getDriver().quit();
		DriverManager.setWebDriver(null);

	}

}
