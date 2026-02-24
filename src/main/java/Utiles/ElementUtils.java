package Utiles;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.List;

/**
 *
 * @author nancy
 */
public class ElementUtils extends DriverManager {

    //Click on an element after waiting for it to be clickable.
    public void click(By locator) {
        WebElement element = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        System.out.println("element clicked " + locator);
    }
    //click on an element
    public void clickElement(WebElement element) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        System.out.println("element clicked " + element);
    }

    //hover over an element
    public void hover(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator))).perform();
        System.out.println("element hovered " + locator);
    }

    //clear
    public void clear(By locator) {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        System.out.println("element cleared " + locator);
    }

    //get list of elements
    public List<WebElement> findElements(By locator) {
        return explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    //Send keys (text) to an element.
    public void sendKeys(By locator, String text) {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
        System.out.println("Send keys to element " + locator);
    }

    //Get visible text from an element.
    public String getText(By locator) {
        String text = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        System.out.println("the visible text from the element is : " + text);
        return text;
    }

    //Select a dropdown option by visible text.
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        new Select(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)))
                .selectByVisibleText(visibleText);
        System.out.println("Selected dropdown value by visible text: " + visibleText);
    }

    //Select a dropdown option by value attribute.
    public void selectDropdownByValue(By locator, String value) {
        new Select(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)))
                .selectByValue(value);
        System.out.println("visible value chosen : " + value);
    }

    //Select a dropdown option by index.
    public void selectDropdownByIndex(By locator, int index) {
        new Select(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)))
                .selectByIndex(index);
        System.out.println("Selected dropdown by index is : " + index);
    }

    //Drag source element and drop it on target element.
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement target = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        new Actions(driver).dragAndDrop(source, target).perform();
        System.out.println("dragged element and dropped it");
    }

    //Check a checkbox if not already checked.
    public void checkCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            System.out.println("checked the checkbox " + locator);
        }
    }

    //Uncheck a checkbox if currently checked.
    public void uncheckCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Checkbox is unchecked");
        }
    }

    //Select a radio button if not already selected.
    public void selectRadioButton(By locator) {
        WebElement radio = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!radio.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
            System.out.println("checked the Radio button " + locator);
        }
    }

}

