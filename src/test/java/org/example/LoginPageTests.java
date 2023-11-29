package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@DisplayName("Example of a Test Class from Home Page Module")
public class LoginPageTests extends BaseTests {
	private static final Logger logger = (Logger) LogManager.getLogger(BaseTests.class);

	@Mock
	private HomePage homePage;

	@Mock
	private WebDriver driver;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Validation Web Element are displayed")
	public void validateSuccessfulNavigationToLoginPage() {
		when(driver.getCurrentUrl()).thenReturn("https://the-internet.herokuapp.com/login");
		logger.info("Assert Navigation Functionality");
		String expectedUrl = "https://the-internet.herokuapp.com/login";
		homePage.navigateToLoginTestingPage();
		assertEquals(driver.getCurrentUrl(), expectedUrl, "Navigation failed.");
		logger.info("Navigation functionality validated");
	}
}
