package Utiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 *
 * @author nancy
 */
public class DriverManager {

    protected static WebDriver driver;
    protected static WebDriverWait explicitWait;

    //Initialize Chrome browser and maximize the window.
    public void initializeBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Browser initialized");
    }

    public WebDriver getDriver() {
        return driver;
    }

    //Set implicit wait timeout for the WebDriver.
    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        System.out.println("set implicit wait to " + seconds);
    }

    //Close the browser and quit the WebDriver session.
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("browser closed");
        }
    }
}


