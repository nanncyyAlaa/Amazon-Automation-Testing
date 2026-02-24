package Pages;

import Utiles.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;  // WebDriver instance for interacting with the browser
    ElementUtils elementUtils = new ElementUtils(); // Utility for element actions

    // Constructor: Initialize WebDriver
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    /*
     * Locators for Cart Page elements
     */
    private final By cartHeader = By.id("sc-active-items-header"); // Cart page header
    private final By productName = By.xpath("//span[contains(@class,'a-truncate-cut')]"); // Product name in cart
    private final By productPrice = By.cssSelector("span.sc-product-price"); // Price of individual product
    private final By productQuantity = By.cssSelector("span[data-a-selector='inner-value']"); // Quantity of product
    private final By productTotal = By.id("sc-subtotal-amount-activecart"); // Total price of cart

    /*
     * Methods to get values from Cart Page
     */

    // Returns the text of the cart header
    public String cartHeaderText(){
        return elementUtils.getText(cartHeader);
    }

    // Returns the product name in the cart
    public String CartProductName(){
        return elementUtils.getText(productName);
    }

    // Returns the product price as a double (removes currency symbols)
    public double CartProductPrice(){
        String price = elementUtils.getText(productPrice).replaceAll("[^0-9.]", "");
        return Double.parseDouble(price);
    }

    // Returns the quantity of the product as an integer
    public int CartProductQuantity(){
        String quantity = elementUtils.getText(productQuantity);
        return Integer.parseInt(quantity);
    }

    // Returns the total cart price as a double (removes currency symbols)
    public double CartProductTotal(){
        String total = elementUtils.getText(productTotal).replaceAll("[^0-9.]", "");
        return Double.parseDouble(total);
    }
}