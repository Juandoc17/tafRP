package org.example;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Logger;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.BDDTesting.testReporter.TestListener;
import org.example.utils.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.google.common.io.Files;


/**
 * BaseTests is a base class for all test classes.
 * This class can include common setup and teardown methods, as well as common utilities
 * for the tests. By extending this class, each test class can avoid code duplication
 * and the tests can be more organized and readable.
 *
 * @author Juan Ocampo
 * @version 1.0
 */
@Listeners(TestListener.class)
public class BaseTests {

	protected RemoteWebDriver driver;
	protected HomePage homePage;
	protected WebDriverWait wait;
	protected static final Logger logger = Logger.getLogger(BaseTests.class.getName());

	@BeforeClass
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		capabilities.setVersion("89.0");
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("enableVideo", false);

		try {
			driver = new RemoteWebDriver(
				URI.create("http://localhost:4444/wd/hub").toURL(), 
				capabilities
			);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		homePage = new HomePage(driver, logger, wait);
	}

	@BeforeMethod
	public void goHome() {
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@AfterMethod
	public void recordFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			var camera = (TakesScreenshot) driver;
			File screenshot = camera.getScreenshotAs(OutputType.FILE);

			String testName = result.getName();
			String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String screenshotName = testName + "_" + dateTime + ".png";

			try {
				Files.move(screenshot, new File("resources/screenshots/" + screenshotName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		//options.setHeadless(true);
		return options;
	}

	public CookieManager getCookieManager() {
		return new CookieManager(driver);
	}

	public WindowManager getWindowManager() {
		return new WindowManager(driver);
	}
}

