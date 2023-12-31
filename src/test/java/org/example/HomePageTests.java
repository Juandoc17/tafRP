package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

/**
 * HomePageTests is a class that extends the BaseTests class.
 * This class contains tests related to the home page functionality of the
 * application.
 * Each test method in this class should correspond to a specific aspect of the
 * home page functionality.
 *
 * @author Juan Ocampo
 * @version 1.0
 */
@DisplayName("Example of a Test Class from Home Page Module")
public class HomePageTests extends BaseTests {
	private static final Logger logger = (Logger) LogManager.getLogger(BaseTests.class);

	@Test
	@DisplayName("Validation of Page Title")
	public void validatePageTitle() {
		logger.info("Assert Page Title");
		String expectedTitle = "Welcome to the-internet";
		assertEquals(homePage.getDashboardTitle(), expectedTitle, "Page title does not match.");
		logger.info("Page title validated");
	}

	@Test
	@DisplayName("Validation of Navigation Functionality")
	public void validateNavigationFunctionality() {
		logger.info("Assert Navigation Functionality");
		String expectedUrl = "https://the-internet.herokuapp.com/abtest";
		homePage.navigateToABTestingPage();
		assertEquals(driver.getCurrentUrl(), expectedUrl, "Navigation failed.");
		logger.info("Navigation functionality validated");
	}

	@Test
	@DisplayName("Validation Web Element are displayed")
	public void validateCriticalElementsAreDisplayedInScreen() {
		logger.info("Assert Home Page Title Page is Displayed");
		assertTrue(homePage.isDashboardDisplayed(), "Dashboard H1 Element not found.");

		logger.info("Assert Links Page are Displayed");
		assertTrue(homePage.isABTestingLinkVisibleAndInteractive(), "AB Link not found.");
		assertTrue(homePage.isAddRemoveElementsLinkVisibleAndInteractive(), "Add Remove Link not found.");
		assertTrue(homePage.isBasicAuthLinkVisibleAndInteractive(), "Basic Auth Link not found.");
		assertTrue(homePage.isCheckBoxesLinkVisibleAndInteractive(), "Check Boxes Link not found.");
		assertTrue(homePage.isLoginLinkVisibleAndInteractive(), "Login Link not found.");
		assertTrue(homePage.isFooterVisibleAndInteractive(), "Footer Link not found.");
		logger.info("All Critical Elements founded");
	}

	@Test
	@DisplayName("Validation of Tag Type Elements")
	public void validateTagTypesOfCriticalElements() {
		logger.info("Assert DashBoard Tag Type");
		assertEquals(homePage.getDashboardTag(), "h1");

		logger.info("Assert Page Links Tags");
		assertEquals(homePage.getABTestingElementTag(), "a");
		assertEquals(homePage.getAddRemoveElementTag(), "a");
		assertEquals(homePage.getCheckBoxesElementTag(), "a");
		logger.info("All Elements with correct Tag Types");
	}

	@Test
	@DisplayName("Validation of Drag and Drop Functionality")
	public void validateDragAndDropFunctionality() {
		logger.info("Assert Drag and Drop Functionality");
		homePage.dragAndDrop(homePage.getDashboardHeader(), homePage.getLoginLink());
		assertTrue(homePage.isDisplayed(homePage.getDragAndDropSucessLocator()));
		logger.info("Drag and Drop functionality validated");
	}

	@Test
	@DisplayName("Validation of Resize Element Functionality")
	public void validateResizeElementFunctionality() {
		logger.info("Assert Resize Element Functionality");
		homePage.resizeElementExample(homePage.getAddRemoveElementsLink(), 50, 50);
		assertTrue(homePage.isDisplayed(homePage.getReSizeSucessLocator()));
		logger.info("Resize Element functionality validated");
	}

	@Test
	@DisplayName("Validation of Scroll To Element Functionality")
	public void validateScrollToElementFunctionality() {
		logger.info("Assert Scroll To Element Functionality");
		homePage.scrollToElement(homePage.getLoginLink());
		assertTrue(homePage.isElementInView(homePage.getLoginLink()), "Element is not in view.");
		logger.info("Scroll To Element functionality validated");
	}

	@Test
	@DisplayName("Validation of Click Using JS Functionality")
	public void validateClickUsingJSFunctionality() {
		String expectedUrl = "https://the-internet.herokuapp.com/addremove";
		logger.info("Assert Click Using JS Functionality");
		homePage.clickUsingJS(homePage.getAddRemoveElementsLink());
		assertEquals(driver.getCurrentUrl(), expectedUrl, "Navigation failed.");
		logger.info("Click Using JS functionality validated");
	}
}
