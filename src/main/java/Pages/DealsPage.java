package Pages;

import Utiles.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DealsPage {
    private WebDriver driver;  // WebDriver instance for interacting with browser
    ElementUtils elementUtils = new ElementUtils(); // Utility class for element actions

    // Constructor: Initialize WebDriver
    public DealsPage(WebDriver driver){
        this.driver = driver;
    }

    /*
     * Locators for Deals Page elements
     */
    private final By Product = By.xpath("//a[@data-testid ='product-card-link']"); // All product links on deals page
    private final By category = By.cssSelector(".a-icon.a-icon-radio"); // Category selection radio buttons
    private final By productName = By.xpath("//span[contains(@class,'a-truncate-full')]"); // Product name (if needed)

    /*
     * Select a category by index
     * @param index - index of the category to click
     */
    public void selectCategory(int index){
        List<WebElement> departments = elementUtils.findElements(category);
        elementUtils.clickElement(departments.get(index));
    }

    /*
     * Select a product by index
     * @param index - index of the product to click
     */
    public void selectProduct(int index){
        List<WebElement> products = elementUtils.findElements(Product);
        elementUtils.clickElement(products.get(index));
    }
}