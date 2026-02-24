package Utiles;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 *
 * @author nancy
 */
public class waitUtils extends DriverManager {

    //Explicitly wait for the presence of an element located by the given locator.
    public void explicitWait(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        System.out.println("explicit wait of an element " + locator);
    }

    //Fluent wait with configurable timeout, polling interval, and custom timeout message.
    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .withMessage(timeoutMessage)
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        System.out.println("fluent wait of an element " + locator);
    }
}
