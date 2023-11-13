package org.example.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import com.mongodb.diagnostics.logging.Logger;


/**
 * The MainFunctional class serves as a base class for other page classes in the application.
 * It encapsulates common functionalities that can be used across different pages, such as filling fields, clicking buttons, and finding elements.
 * This class uses the Page Object Model design pattern, which improves test maintenance and reduces code duplication.
 */
public class MainFunctional {
	protected WebDriver driver;
	protected Logger logger;

	public MainFunctional(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
	}

	protected void fillField(WebElement field, String value) {
		field.sendKeys(value);
	}

	protected void clickButton(WebElement button) {
		button.click();
	}

	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public void hoverElement(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(findElement(locator)).perform();
	}

	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.error("Element not found: " + locator, e);
			return false;
		}
	}
}
