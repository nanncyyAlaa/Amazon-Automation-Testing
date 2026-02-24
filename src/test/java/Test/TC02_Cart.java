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

public class TC02_Cart {
    WebDriver driver;
    private HomePage homePage;
    private DealsPage dealsPage;
    private CartPage cartPage;
    private ProductPage productPage;
    private BrowserUtils browser;
    private DriverManager driverManager;

    // Test case: Add product to cart and verify details
    @Test
    public void TestCart(){
        // Navigate to Amazon homepage and access Today's Deals section
        browser.navigateToURL("https://www.amazon.com/");
        homePage.clickDealsNav();

        // Select second category and first product from Deals page
        dealsPage.selectCategory(1);
        dealsPage.selectProduct(1);

        // Get product price and other details
        double expectedPrice = productPage.getProductPrice();
        productPage.clickSecondItem();
        productPage.clickQuantityList();
        productPage.clickQuantity();
        String Expected = productPage.getProductTitle();
        int expectedQuantity = productPage.getQuantity();
        double expectedTotal = expectedPrice * expectedQuantity;

        System.out.println("Expected Price: " + expectedPrice + " Expected Quantity: " + expectedQuantity + " Expected Total: " + expectedTotal);

        // Add product to cart and navigate to Cart page
        productPage.clickAddToCartButton();
        homePage.clickCartNav();

        // Verify that cart page is displayed
        assertTrue(cartPage.cartHeaderText().contains("Cart"),"not navigated to cart page");
        System.out.println("CartHeader text: " + cartPage.cartHeaderText());

        // Capture actual product details from cart
        String Actual = cartPage.CartProductName();
        double actualPrice = cartPage.CartProductPrice();
        int ActualQuantity = cartPage.CartProductQuantity();
        double ActualTotal = cartPage.CartProductTotal();

        // Assertions to verify cart content matches selected product
        assertTrue(Expected.contains(Actual.split(" ")[3]),"The product name in the cart does not match the selected product");
        assertEquals(expectedPrice,actualPrice,"The product price in the cart is incorrect");
        assertEquals(expectedQuantity,ActualQuantity,"The product quantity in the cart is incorrect");
        assertEquals(expectedTotal,ActualTotal,0.1,"The product total in the cart is incorrect");
    }

    // Before running the class: Initialize browser and driver
    @BeforeClass
    public void SetupClass() {
        driverManager = new DriverManager();
        driverManager.initializeBrowser();
        System.out.println("Setup done TC02");
    }

    // After all tests: Close browser
    @AfterClass
    public void TearDownClass() {
        driverManager.closeBrowser();
        System.out.println("TearDown done TC02");
    }

    // Before each test method: Initialize page objects
    @BeforeMethod
    public void SetupMethod() {
        homePage = new HomePage(driver);
        dealsPage = new DealsPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
        browser = new BrowserUtils();
        System.out.println("Setup method done TC02");
    }

    // After each test method: Placeholder for cleanup or logging
    @AfterMethod
    public void recordFailure(ITestResult result) {
        WebDriver driver = driverManager.getDriver();
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot: " + result.getName(),
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    ".png");
        }
    }
}
