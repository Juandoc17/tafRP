package org.example.BDDTesting.stepsDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.BaseTests;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;

import io.cucumber.java.en.*;

/**
 * This class contains the step definitions for the login functionalities.
 * Each method corresponds to a step in the Cucumber scenarios.
 */
public class LoginFunctionalitiesSteps extends BaseTests {
	private LoginPage loginPage;
	private HomePage homePage;

	/**
	 * This method is run when the user navigates to the login page.
	 */
	@Given("the user has navigated to the login page")
	public void theUserHasNavigatedToTheLoginPage() {
		homePage.navigateToLoginTestingPage();
	}

	/**
	 * This method is run when the user enters the username and password.
	 * @param username The username entered by the user.
	 * @param password The password entered by the user.
	 */
	@When("the user enters username {string} and password {string}")
	public void theUserEntersUsernameAndPassword(String username, String password) {
		loginPage.fillUserNameField(username);
		loginPage.fillPasswordField(password);
	}

	/**
	 * This method is run when the user clicks on the 'login' button.
	 */
	@And("user click on 'login' button")
	public void theUserClickLoginButton() {
		loginPage.clickLoginButton();
	}

	/**
	 * This method is run when the user should be redirected to the secure page.
	 */
	@Then("the user should be redirected to the secure page")
	public void theUserShouldBeRedirectedToTheSecurePage() {
		assertEquals("https://the-internet.herokuapp.com/secure", driver.getCurrentUrl());
	}

	/**
	 * This method is run when the user should see an error message indicating invalid login credentials.
	 */
	@Then("the user should see an error message {string}")
	public void theUserShouldSeeAnErrorMessage(String errorMessage) {
		assertTrue(loginPage.isErrorMessageDisplayed());
		assertEquals(loginPage.getErrorMessage(), errorMessage);
	}
}
