package org.example.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.example.pages.selenide.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class HomePageTest {
    HomePage homePage = new HomePage();

    @Before
    public void setUp() {
        Configuration.timeout = 5000;
        Configuration.screenshots = true;
        Configuration.reportsFolder = "test-result/reports";
    }

    @Test
    public void testHomePage() {
        open("https://the-internet.herokuapp.com/");

        try {
            //homePage.dashboardHeader.shouldBe(visible);
            //assertTrue(homePage.isDashboardDisplayed());
        } catch (TimeoutException e) {
            Selenide.screenshot("dashboard-display-failure");
            throw e;
        }
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
