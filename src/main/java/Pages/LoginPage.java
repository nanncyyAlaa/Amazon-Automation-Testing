package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utiles.*;

public class LoginPage {
    WebDriver driver;  // WebDriver instance for interacting with browser
    ElementUtils elementUtils = new ElementUtils(); // Utility class for element actions

    // Constructor: Initialize WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Locators for Login Page elements
     */
    private final By LoginHeader = By.xpath("//h1[contains(text(),'Sign in')]"); // Login page header
    private final By Email = By.id("ap_email_login"); // Email input field on login page
    private final By emailAccess = By.id("ap_email"); // Email input field for access page
    private final By continueBtn = By.className("a-button-input"); // Continue button after email
    private final By notRegisteredEmail = By.xpath("//h1[contains(text(),'Looks')]"); // "Looks like you're new" message
    private final By homeNav = By.cssSelector("a.a-link-nav-icon"); // Amazon home icon link
    private final By noAccHeading = By.xpath("//h4[contains(text(),\"There was a problem\")]"); // Problem message for no account

    /*
     * Methods to interact with Login Page
     */

    // Get the header text of login page
    public String getHeader(){
        return elementUtils.getText(LoginHeader);
    }

    // Enter email in login field
    public void EnterEmail(String email){
        elementUtils.sendKeys(Email,email);
    }

    // Click continue button
    public void clickContinueBtn(){
        elementUtils.click(continueBtn);
    }

    // Full login method (enter email + click continue)
    public void login(String email){
        EnterEmail(email);
        clickContinueBtn();
    }

    // Enter email in access page (alternate login flow)
    public void loginAccess(String email){
        elementUtils.sendKeys(emailAccess,email);
    }
    
     // Return text of "There was a problem" heading
     public String getProblemHeading(){
        return elementUtils.getText(noAccHeading); 
    }

    // Get the "not registered" message text
    public String getNotRegisteredText(){
        return elementUtils.getText(notRegisteredEmail);
    }

    // Click Amazon home icon to return to homepage
    public void clickAmazonIcon(){
        elementUtils.click(homeNav);
    }
}
