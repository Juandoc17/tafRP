package org.example.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;


/**
 * The MainFunctional class serves as a base class for other page classes in the application.
 * It encapsulates common functionalities that can be used across different pages, such as filling fields, clicking buttons, and finding elements.
 * This class uses the Page Object Model design pattern, which improves test maintenance and reduces code duplication.
 */
public class MainFunctional {
	protected WebDriver driver;
	protected Logger logger;
	protected WebDriverWait wait;
	public MainFunctional(WebDriver driver, Logger logger,WebDriverWait wait) {
		this.driver = driver;
		this.logger = logger;
		this.wait = wait;
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.info("Element not found: " + locator);
			return false;
		}
	}

	public Boolean isClickable(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.info("Element not clickable: " + locator);
			return false;
		}
	}
	public String getElementTag(WebElement webElement) {
		return webElement.getTagName();
	}
}
