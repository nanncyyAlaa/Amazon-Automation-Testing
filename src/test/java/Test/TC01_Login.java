package Test;

import static org.testng.Assert.*;
import java.io.ByteArrayInputStream;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import Pages.*;
import Utiles.*;

public class TC01_Login {
    WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private BrowserUtils browser;
    private DriverManager driverManager;

    /*
     * Test Scenario Summary:
     * ----------------------
     * 1. Navigate to Amazon homepage.
     * 2. Click on the Login navigation link.
     * 3. Verify the login page header is displayed correctly.
     * 4. Attempt login with a new/unregistered email.
     * 5. Verify that the "Looks like you're new to Amazon" message is displayed.
     * 6. Capture screenshot if the test fails and attach it to Allure report.
     */

    // Test case: Verify login attempt with unregistered user
    @Test
    public void TC01_TestLogin() {
        // Navigate to Amazon homepage
        browser.navigateToURL("https://www.amazon.com/");

        // Click login and verify login page is displayed
        homePage.clickLoginNav();
        assertEquals(loginPage.getHeader(),"Sign in or create account");
        System.out.println("login page is displayed");

        // Attempt login with unregistered email
        loginPage.login("nanncyyalaa@gmail.com");

        // Verify the "not registered" message
        assertTrue(loginPage.getNotRegisteredText().contains("Looks like you're new to Amazon"),
                "error , user should not login ");
        System.out.println("you're not registered page is displayed");
    }

    // Before class: Initialize browser driver
    @BeforeClass
    public void SetupClass() {
        driverManager = new DriverManager();
        driverManager.initializeBrowser();
        System.out.println("Setup done TC01");
    }

    // After class: Close browser
    @AfterClass
    public void TearDownClass() {
        driverManager.closeBrowser();
        System.out.println("TearDown done TC01");
    }

    // Before each test method: Initialize page objects and browser utils
    @BeforeMethod
    public void SetupMethod() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        browser = new BrowserUtils();
        System.out.println("Setup method done TC01");
    }

    // After each test: Capture screenshot if test fails and attach to Allure report
    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot: " + result.getName(),
                    new ByteArrayInputStream(screenshotBytes)
            );
        }
    }
}


