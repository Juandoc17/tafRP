package org.example;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@DisplayName("Example of a Test Class from Login Module")
public class LoginTests extends BaseTests {

    private EventFiringWebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    @Test
    @DisplayName("Example of a Login Test Method")
    public void testLogin() {
        // Find the username and password fields and the login button
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        logger.info("Typing the credentials in LoginPage");
        usernameField.sendKeys("your_username");
        passwordField.sendKeys("your_password");

        logger.info("Login Action Button");
        loginButton.click();
        logger.info("Clicked the login button.");

        WebElement dashboardHeader = driver.findElement(By.id("dashboardHeader"));
        assert dashboardHeader.isDisplayed() : "Login failed.";
        logger.info("Login successful.");
    }

}
