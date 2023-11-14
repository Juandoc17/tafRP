package org.example.pages;

import org.openqa.selenium.*;
import java.util.logging.Logger;

/**
 * The HomePage class represents the home page of the application.
 * It is responsible for displaying the main content to users after they have successfully logged in.
 * This includes navigation menus, user-specific information, and other key features of the application.
 */
public class HomePage extends MainFunctional {

	private By dashboardHeaderLocator = By.cssSelector("h1.heading");
	private By aBTestingLinkLocator = By.cssSelector("a[href='/abtest']");
	private By addRemoveElementsLinkLocator = By.cssSelector("a[href='/add_remove_elements/']");
	private By basicAuthLinkLocator = By.cssSelector("a[href='/basic_auth']");
	private By checkBoxesLinkLocator = By.cssSelector("a[href='/checkboxes']");
	private By loginLinkLocator = By.cssSelector("a[href='/login']");
	private By footerLinksLocator = By.cssSelector("div div div");

	public HomePage(WebDriver driver, Logger logger) {
		super(driver, logger);
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

	public String getDashboardTitle(){
		return getText(dashboardHeaderLocator);
	}

	public String getDashboardTag(){
		return getElementTag(findElement(dashboardHeaderLocator));
	}

	public String getABTestingElementTag(){
		return getElementTag(findElement(aBTestingLinkLocator));
	}
	public String getAddRemoveElementTag(){
		return getElementTag(findElement(addRemoveElementsLinkLocator));
	}
	public String getCheckBoxesElementTag(){
		return getElementTag(findElement(checkBoxesLinkLocator));
	}

	public void navigateToABTestingPage(){
		clickButton(driver.findElement(aBTestingLinkLocator));
	}

	public void navigateToLoginTestingPage(){
		clickButton(driver.findElement(loginLinkLocator));
	}
}
