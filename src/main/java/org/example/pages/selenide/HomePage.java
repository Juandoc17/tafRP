package org.example.pages.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.openqa.selenium.TakesScreenshot;

/**
 * The HomePage class represents the home page of the application.
 * It is responsible for displaying the main content to users after they have successfully logged in.
 * This includes navigation menus, user-specific information, and other key features of the application.
 */
public class HomePage {

	private SelenideElement dashboardHeader = $("h1.heading");
	private SelenideElement aBTestingLink = $("a[href='/abtest']");
	private SelenideElement addRemoveElementsLink = $("a[href='/add_remove_elements/']");
	private SelenideElement basicAuthLink = $("a[href='/basic_auth']");
	private SelenideElement checkBoxesLink = $("a[href='/checkboxes']");
	private SelenideElement loginLink = $("a[href='/login']");
	private SelenideElement footerLinks = $("div div div");

    public boolean isDashboardDisplayed() {
        return performAction("Check if dashboard is displayed", () -> dashboardHeader.shouldBe(visible).isDisplayed());
    }

	public boolean isABTestingLinkVisibleAndInteractive() {
		return aBTestingLink.isDisplayed() && aBTestingLink.isEnabled();
	}

	public boolean isAddRemoveElementsLinkVisibleAndInteractive() {
		return addRemoveElementsLink.isDisplayed() && addRemoveElementsLink.isEnabled();
	}

	public boolean isBasicAuthLinkVisibleAndInteractive() {
		return basicAuthLink.isDisplayed() && basicAuthLink.isEnabled();
	}

	public boolean isCheckBoxesLinkVisibleAndInteractive() {
		return checkBoxesLink.isDisplayed() && checkBoxesLink.isEnabled();
	}

	public boolean isFooterVisibleAndInteractive() {
		return footerLinks.isDisplayed() && footerLinks.isEnabled();
	}

	public boolean isLoginLinkVisibleAndInteractive() {
		return loginLink.isDisplayed() && loginLink.isEnabled();
	}

	public String getDashboardTitle(){
		return dashboardHeader.text();
	}

	public String getDashboardTag(){
		return dashboardHeader.getTagName();
	}

	public String getABTestingElementTag(){
		return aBTestingLink.getTagName();
	}

	public String getAddRemoveElementTag(){
		return addRemoveElementsLink.getTagName();
	}

	public String getCheckBoxesElementTag(){
		return checkBoxesLink.getTagName();
	}

	public void navigateToABTestingPage(){
		aBTestingLink.click();
	}

	public void navigateToLoginTestingPage(){
		loginLink.click();
	}
    
    private <T> T performAction(String actionDescription, Action<T> action) {
        try {
            System.out.println(actionDescription);  // Logging the action
            return action.perform();
        } catch (UIAssertionError e) {
            takeScreenshot(actionDescription);
            throw e;
        }
    }

    private void takeScreenshot(String actionDescription) {
        File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get("./screenshots/" + actionDescription + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    private interface Action<T> {
        T perform();
    }
}
