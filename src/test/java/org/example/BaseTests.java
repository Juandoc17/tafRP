package org.example;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.BDDTesting.testReporter.TestListener;
import org.example.integrations.JiraService;
import org.example.integrations.SlackService;
import org.example.utils.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.google.common.io.Files;

/**
 * BaseTests is a base class for all test classes.
 * This class can include common setup and teardown methods, as well as common
 * utilities
 * for the tests. By extending this class, each test class can avoid code
 * duplication
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
	private static final String userName = System.getProperty("jira.username");
	private static final String password = System.getProperty("jira.password");
	protected static final Logger logger = Logger.getLogger(BaseTests.class.getName());
	private static final SlackService slackNotifier = new SlackService();
	private static int passed = 0, failed = 0, skipped = 0;
	private static final JiraService jiraService = new JiraService(
        "https://your-jira-instance.atlassian.net",
        userName,
        password
    );

    @BeforeClass
    public void setUp() {
        DesiredCapabilities capabilities = getSauceLabsCapabilities();
        try {
            driver = new RemoteWebDriver(
                    new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        homePage = new HomePage(driver, logger, wait);
    }

	private static DesiredCapabilities getSauceLabsCapabilities(ITestContext testContext) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "macOS 14");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("sauce:options", Map.of(
			"username", "oauth-juan.doc27-f87fc",
			"accessKey", "9faab1c6-c67a-423c-8302-80a9ecd7016b",
			"build", "build-1234",
			"name", testContext.getName()
		));
		return capabilities;
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
			jiraService.createIssue("Test failed: " + result.getName());
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			passed++;
		} else if (result.getStatus() == ITestResult.FAILURE) {
			failed++;
		} else if (result.getStatus() == ITestResult.SKIP) {
			skipped++;
		}
	}

	@AfterClass
	public void afterClass() {
		String message = String.format("Tests completed. %d passed, %d failed, %d skipped.", passed, failed, skipped);
		slackNotifier.postNotification(message);
	}

	private ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		// options.setHeadless(true);
		return options;
	}

	public CookieManager getCookieManager() {
		return new CookieManager(driver);
	}

	public WindowManager getWindowManager() {
		return new WindowManager(driver);
	}
}
