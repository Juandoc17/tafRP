package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

/**
 * The HomePage class represents the home page of the application.
 * It is responsible for displaying the main content to users after they have
 * successfully logged in.
 * This includes navigation menus, user-specific information, and other key
 * features of the application.
 */
public class HomePage extends MainFunctional {

	private By dashboardHeaderLocator = By.cssSelector("h1.heading");
	private By aBTestingLinkLocator = By.cssSelector("a[href='/abtest']");
	private By addRemoveElementsLinkLocator = By.cssSelector("a[href='/add_remove_elements/']");
	private By basicAuthLinkLocator = By.cssSelector("a[href='/basic_auth']");
	private By checkBoxesLinkLocator = By.cssSelector("a[href='/checkboxes']");
	private By loginLinkLocator = By.cssSelector("a[href='/login']");
	private By footerLinksLocator = By.cssSelector("div div div");
	private By dragAndDropSuccessLocator = By.cssSelector("div.dragAndDrop");
	private By reSizeSuccessLocator = By.cssSelector("div.reSizeSucc");

	public HomePage(WebDriver driver, Logger logger, WebDriverWait wait) {
		super(driver, logger, wait);
	}

	public boolean isDashboardDisplayed() {
		return isDisplayed(dashboardHeaderLocator);
	}

	public boolean isABTestingLinkVisibleAndInteractive() {
		return isDisplayed(aBTestingLinkLocator) && isClickable(aBTestingLinkLocator);
	}

	public boolean isAddRemoveElementsLinkVisibleAndInteractive() {
		return isDisplayed(addRemoveElementsLinkLocator) && isClickable(addRemoveElementsLinkLocator);
	}

	public boolean isBasicAuthLinkVisibleAndInteractive() {
		return isDisplayed(basicAuthLinkLocator) && isClickable(basicAuthLinkLocator);
	}

	public boolean isCheckBoxesLinkVisibleAndInteractive() {
		return isDisplayed(checkBoxesLinkLocator) && isClickable(checkBoxesLinkLocator);
	}

	public boolean isFooterVisibleAndInteractive() {
		return isDisplayed(footerLinksLocator) && isClickable(footerLinksLocator);
	}

	public boolean isLoginLinkVisibleAndInteractive() {
		return isDisplayed(loginLinkLocator) && isClickable(loginLinkLocator);
	}

	public String getDashboardTitle() {
		return getText(dashboardHeaderLocator);
	}

	public String getDashboardTag() {
		return getElementTag(findElement(dashboardHeaderLocator));
	}

	public String getABTestingElementTag() {
		return getElementTag(findElement(aBTestingLinkLocator));
	}

	public String getAddRemoveElementTag() {
		return getElementTag(findElement(addRemoveElementsLinkLocator));
	}

	public String getCheckBoxesElementTag() {
		return getElementTag(findElement(checkBoxesLinkLocator));
	}

	public WebElement getDashboardHeader() {
		return driver.findElement(dashboardHeaderLocator);
	}

	public WebElement getABTestingLink() {
		return driver.findElement(aBTestingLinkLocator);
	}

	public WebElement getAddRemoveElementsLink() {
		return driver.findElement(addRemoveElementsLinkLocator);
	}

	public WebElement getBasicAuthLink() {
		return driver.findElement(basicAuthLinkLocator);
	}

	public WebElement getCheckBoxesLink() {
		return driver.findElement(checkBoxesLinkLocator);
	}

	public WebElement getLoginLink() {
		return driver.findElement(loginLinkLocator);
	}

	public By getDragAndDropSucessLocator() {
		return dragAndDropSuccessLocator;
	}
	public By getReSizeSucessLocator() {
		return reSizeSuccessLocator;
	}

	public WebElement getFooterLinks() {
		return driver.findElement(footerLinksLocator);
	}

	public void navigateToABTestingPage() {
		clickButton(driver.findElement(aBTestingLinkLocator));
	}

	public void navigateToLoginTestingPage() {
		clickButton(driver.findElement(loginLinkLocator));
	}

	public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, targetElement).build().perform();
	}

	public void resizeElementExample(WebElement resizeableElement, int xOffset, int yOffset) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(resizeableElement).moveByOffset(xOffset, yOffset).release().build().perform();
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public boolean isElementInView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (Boolean) js.executeScript("return arguments[0].getBoundingClientRect().top >= 0 && " +
				"arguments[0].getBoundingClientRect().left >= 0 && " +
				"arguments[0].getBoundingClientRect().bottom <= window.innerHeight && " +
				"arguments[0].getBoundingClientRect().right <= window.innerWidth;", element);
	}

	public void clickUsingJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}
