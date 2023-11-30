package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

/**
 * The LoginPage class represents the login page of the application.
 * It is responsible for handling login functionalities, including sign-in operations and providing assistance to users.
 */
public class LoginPage extends MainFunctional {

	private WebElement usernameField = driver.findElement(By.id("username"));
	private WebElement passwordField = driver.findElement(By.id("password"));
	private WebElement loginButton = driver.findElement(By.id("button.radius"));
	private By loginSuccessfulMessageLocator = By.cssSelector("#flash.success");
	private WebElement loginSuccessfulMessage = driver.findElement(loginSuccessfulMessageLocator);
	private WebElement loginErrorMessage = driver.findElement(By.id("flash"));

	public LoginPage(WebDriver driver, Logger logger, WebDriverWait wait) {
		super(driver, logger, wait);
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

	public String getErrorMessage(){
		return loginErrorMessage.getText();
	}

	public boolean isErrorMessageDisplayed() {
		try {
			return loginErrorMessage.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public boolean isLoginSuccessfulMessageDisplayed() {
		return isDisplayed(loginSuccessfulMessageLocator);
	}
}
