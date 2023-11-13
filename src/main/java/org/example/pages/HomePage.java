package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mongodb.diagnostics.logging.Logger;


/**
 * The HomePage class represents the home page of the application.
 * It is responsible for displaying the main content to users after they have successfully logged in.
 * This includes navigation menus, user-specific information, and other key features of the application.
 */
public class HomePage extends MainFunctional {

	private By dashboardHeaderLocator = By.id("dashboardHeader");

	public HomePage(WebDriver driver, Logger logger) {
		super(driver, logger);
	}

	public boolean checkPageIsDisplayed() {
		return isDisplayed(dashboardHeaderLocator);
	}
}
