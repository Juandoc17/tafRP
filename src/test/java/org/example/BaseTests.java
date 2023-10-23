package org.example;

import com.google.common.io.Files;

import org.example.pages.HomePage;
import org.example.utils.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;

@Listeners(TestListener.class)
public class BaseTests {

    private EventFiringWebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/drivers/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://www.reportportal.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("resources/screenshots/" + "errorExecution" + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        //options.setHeadless(true);
        return options;
    }

    public CookieManager getCookieManager(){
        return new CookieManager(driver);
    }
}
