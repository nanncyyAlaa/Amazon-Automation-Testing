package Test;

import Pages.*;
import Utiles.BrowserUtils;
import Utiles.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.*;

public class TC03_Access {
    WebDriver driver;
    private HomePage homePage;
    private DealsPage dealsPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private BrowserUtils browser;
    private DriverManager driverManager;

    /*
     * Test Scenario Summary:
     * ----------------------
     * 1. Navigate to Amazon homepage.
     * 2. Attempt to access account-related sections like Orders and Addresses (triggers login if not signed in).
     * 3. Perform login using valid credentials.
     * 4. Navigate to Account -> Lists and verify correct page headings.
     * 5. Include a deliberately failing test to verify that screenshots are captured on failure and attached to Allure reports.
     */

    // Test case: Verify account access flow and navigation through account sections
    @Test
    public void AccountAccess() {
        // Navigate to Amazon homepage
        browser.navigateToURL("https://www.amazon.com/");

        // Attempt to access Orders (requires login)
        homePage.hoverOverAccAndLists();
        homePage.clickOrdersNav();
        assertTrue(loginPage.getHeader().contains("Sign in"),"unexpected action");

        // Login process
        loginPage.loginAccess("nanncyyalaa@gmail.com");
        loginPage.clickContinueBtn();
        loginPage.clickAmazonIcon();

        // Navigate to Account -> Addresses (requires login)
        homePage.hoverOverAccAndLists();
        homePage.clickAccountNav();
        homePage.clickAddressesNav();
        assertTrue(loginPage.getHeader().contains("Sign in"),"unexpected action");

        // Complete login and navigate back to Lists
        loginPage.login("nanncyyalaa@gmail.com");
        loginPage.clickContinueBtn();
        loginPage.clickAmazonIcon();
        homePage.hoverOverAccAndLists();
        homePage.clickAccountNav();
        homePage.clickListsNav();
        assertTrue(homePage.getListsHeading().contains("Lists"),"unexpected action");
    }

    // Test case: Intentionally failing test to validate screenshot capture
    @Test
    public void failureTest() {
        browser.navigateToURL("https://www.amazon.com/");
        homePage.clickCartNav();

        // This assertion is expected to fail to trigger screenshot capture
        assertTrue(cartPage.cartHeaderText().contains("login"),"it's cart page");
    }

    // Before class: Initialize browser driver
    @BeforeClass
    public void SetupClass() {
        driverManager = new DriverManager();
        driverManager.initializeBrowser();
        System.out.println("Setup done TC03");
    }

    // Before each test: Initialize page objects and utilities
    @BeforeMethod
    public void SetupMethod() {
        homePage = new HomePage(driver);
        dealsPage = new DealsPage(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        browser = new BrowserUtils();
        System.out.println("Setup method done TC03");
    }

    // After each test: Capture screenshot if test fails and attach to Allure report
    @AfterMethod
    public void recordFailure(ITestResult result) {
        WebDriver driver = driverManager.getDriver();
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot: " + result.getName(),
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    ".png"
            );
        }
    }

    // After class: Close browser
    @AfterClass
    public void TearDownClass() {
        driverManager.closeBrowser();
        System.out.println("TearDown done TC03");
    }
}


