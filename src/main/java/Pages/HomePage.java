package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utiles.*;

public class HomePage {
    private WebDriver driver;  // WebDriver instance for interacting with the browser
    ElementUtils elementUtils = new ElementUtils(); // Utility class for element interactions

    // Constructor: Initialize WebDriver
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    /*
     * Locators for Home Page elements
     */
    private final By loginNav = By.cssSelector("a[data-nav-role='signin']"); // Login navigation link
    private final By cartNav = By.xpath("//a[@id='nav-cart']"); // Cart page link
    private final By DealsNav = By.xpath("//a[contains(text(),'Today')]"); // Deals navigation link
    private final By accountNav = By.xpath("//span[@class = 'nav-text' and contains(text(),'Account')]"); // Account link
    private final By ordersNav = By.id("nav_prefetch_yourorders"); // Orders navigation link
    private final By addressesNav= By.xpath(" //div[@data-card-identifier='AddressesAnd1Click']"); // Addresses section
    private final By listsNav= By.xpath(" //div[@data-card-identifier='YourLists_C']"); // Lists section
    private final By noAccHeading = By.xpath("//h4[contains(text(),\"There was a problem\")]"); // Problem message for no account
    private final By listsHeader = By.cssSelector("div.intro-page-banner-header"); // Lists page heading

    /*
     * Methods to interact with Home Page
     */

    public void clickLoginNav() {
        elementUtils.click(loginNav);
    }

    public void clickCartNav() {
        elementUtils.click(cartNav);
    }

    public void clickDealsNav() {
        elementUtils.click(DealsNav);
    }

    public void clickAccountNav() {
        elementUtils.click(accountNav);
    }

    public void hoverOverAccAndLists(){
        elementUtils.hover(loginNav); // Hover over login for dropdown menu
    }

    public void clickOrdersNav() {
        elementUtils.click(ordersNav);
    }

    public void clickAddressesNav() {
        elementUtils.click(addressesNav);
    }

    public void clickListsNav() {
        elementUtils.click(listsNav);
    }

    public String getListsHeading(){
        return elementUtils.getText(listsHeader); // Return Lists page heading
    }
}