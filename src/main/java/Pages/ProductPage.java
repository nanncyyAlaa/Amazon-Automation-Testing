package Pages;

import Utiles.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;  // WebDriver instance for interacting with browser
    ElementUtils elementUtils = new ElementUtils(); // Utility class for element actions

    // Constructor: Initialize WebDriver
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Locators for Product Page elements
     */
    private final By productTitle = By.id("productTitle"); // Product name/title
    private final By productPrice = By.cssSelector("#corePriceDisplay_desktop_feature_div .a-price-whole"); // Whole part of product price
    private final By fractionPrice = By.cssSelector("#corePriceDisplay_desktop_feature_div .a-price-fraction"); // Fraction part of product price
    private final By secondItem = By.xpath("//input[@name = '1']"); // Second product variant/item option
    private final By quantityList = By.xpath("//span[@data-action='a-dropdown-button']"); // Quantity dropdown
    private final By selectQuantity = By.xpath("//a[@id='quantity_1']"); // Quantity option to select
    private final By addToCartButton = By.name("submit.add-to-cart"); // Add to cart button

    /*
     * Methods to interact with Product Page
     */

    // Get the product title text
    public String getProductTitle(){
        return elementUtils.getText(productTitle);
    }

    // Get the full product price as double (combines whole and fraction parts)
    public double getProductPrice(){
        String whole = elementUtils.getText(productPrice).replaceAll("[^0-9.]", "");
        String fraction = elementUtils.getText(fractionPrice);
        return Double.parseDouble(whole + "." + fraction);
    }

    // Click the second item/variant if available
    public void clickSecondItem(){
        elementUtils.click(secondItem);
    }

    // Open the quantity dropdown list
    public void clickQuantityList(){
        elementUtils.click(quantityList);
    }

    // Select a specific quantity from the dropdown
    public void clickQuantity(){
        elementUtils.click(selectQuantity);
    }

    // Get the currently selected quantity as integer
    public int getQuantity(){
        String quantity = elementUtils.getText(quantityList).replaceAll("[^0-9.]", "");
        return Integer.parseInt(quantity);
    }

    // Click "Add to Cart" button
    public void clickAddToCartButton(){
        elementUtils.click(addToCartButton);
    }
}