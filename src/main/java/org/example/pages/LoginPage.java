package org.example.pages;

import org.openqa.selenium.*;

import com.mongodb.diagnostics.logging.Logger;


/**
 * The LoginPage class represents the login page of the application.
 * It is responsible for handling login functionalities, including sign-in operations and providing assistance to users.
 */
public class LoginPage extends MainFunctional {

	private WebElement usernameField = driver.findElement(By.id("username"));
	private WebElement passwordField = driver.findElement(By.id("password"));
	private WebElement loginButton = driver.findElement(By.id("loginButton"));

	public LoginPage(WebDriver driver, Logger logger) {
		super(driver, logger);
	}

	public void fillUserNameField(String username) {
		fillField(usernameField, username);
	}

	public void fillPasswordField(String password) {
		fillField(passwordField, password);
	}

	public void clickLoginButton() {
		clickButton(loginButton);
	}
}