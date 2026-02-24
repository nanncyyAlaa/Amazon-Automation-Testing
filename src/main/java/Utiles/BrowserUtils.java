package Utiles;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 *
 * @author nancy
 */
public class BrowserUtils extends DriverManager {

    //Navigate to the specified URL in the browser.
    public void navigateToURL(String url) {
        driver.get(url);
        System.out.println("navigated to url " + url);
    }

    //Return the current page title.
    public String getPageTitle() {
        return driver.getTitle();
    }

    //Return the current page URL.
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    //Navigate back in browser history.
    public void navigateBack() {
        driver.navigate().back();
        System.out.println("navigated Back");
    }

    //Navigate forward in browser history.
    public void navigateForward() {
        driver.navigate().forward();
        System.out.println("navigated forward");
    }

    //Refresh the current page.
    public void refreshPage() {
        driver.navigate().refresh();
        System.out.println("page refreshed");
    }

    //Switch browser window focus to window with given title.
    public void switchToWindowByTitle(String windowTitle) {
        String currentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(windowTitle)) return;
        }
        driver.switchTo().window(currentWindow);
    }

    //Accept (click OK on) an active JavaScript alert.
    public void acceptAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent())
                .accept();
        System.out.println("Alert Accepted");
    }

    //Dismiss (click Cancel on) an active JavaScript alert.
    public void dismissAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent())
                .dismiss();
        System.out.println("Alert Dismissed");
    }

    //Get the text content of an active JavaScript alert.
    public String getAlertText() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    //Send text input to a prompt alert and accept it.
    public void sendTextToAlert(String text) {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        System.out.println("Sent text to alert and accepted " + text);
    }

}
