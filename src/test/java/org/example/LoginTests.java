package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import com.mongodb.diagnostics.logging.Logger;


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
	private static final Logger logger = (Logger) LogManager.getLogger(BaseTests.class);

	/**
	 * This method is a Base for Future LoginTest.
	 * Check Successful Login Functionalities
	 */
	@Test
	@DisplayName("Example of a Login Test Method")
	public void testLogin() {

		logger.info("Typing the credentials in LoginPage");
		loginPage.fillUserNameField("Juan");
		loginPage.fillPasswordField("MyPassword");

		logger.info("Login Action Button");
		loginPage.clickLoginButton();
		logger.info("Clicked the login button.");

		assertTrue(homePage.checkPageIsDisplayed(), "Login failed.");
		logger.info("Login successful.");
	}
}