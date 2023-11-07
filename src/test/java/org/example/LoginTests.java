package org.example;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoginTests is a class that extends the BaseTests class.
 * This class contains tests related to the login functionality of the application.
 * Each test method in this class should correspond to a specific aspect of the login functionality.
 *
 * @author Juan Ocampo
 * @version 1.0
 */
@DisplayName("Example of a Test Class from Login Module")
public class LoginTests extends BaseTests {
    private static final Logger logger = LogManager.getLogger(LoginTests.class);
    private WebElement usernameField = driver.findElement(By.id("username"));
    private WebElement passwordField = driver.findElement(By.id("password"));
    private WebElement loginButton = driver.findElement(By.id("loginButton"));

    /**
     * This method is a Base for Future LoginTest.
     * Check Successful Login Functionalities
     */
    @Test
    @DisplayName("Example of a Login Test Method")
    public void testLogin() {

        logger.info("Typing the credentials in LoginPage");
        fillUserNameField("Juan");
        fillPasswordField("MyPassword");

        logger.info("Login Action Button");
        loginButton.click();
        logger.info("Clicked the login button.");

        WebElement dashboardHeader = driver.findElement(By.id("dashboardHeader"));
        assert dashboardHeader.isDisplayed() : "Login failed.";
        logger.info("Login successful.");
    }
    public void fillUserNameField(String username){
        usernameField.sendKeys(username);
    }
    public void fillPasswordField(String password){
        passwordField.sendKeys(password);
    }
}
