# Amazon Automation Project

## **Project Overview**

This project automates testing of **Amazon.eg** website using **Selenium WebDriver**, **TestNG**, and **Allure Reports**.

The project is designed using the **Page Object Model (POM)** to separate page interactions from test logic for maintainability and reusability.

---

## **Technical Task Scenarios Implemented**

### **Scenario 1 – Verify login with unregistered email**

* Navigate to [Amazon Egypt](https://www.amazon.eg/).
* Click **Login**.
* Enter a valid but **unregistered email**.
* Verify that the login fails and a message like `"Looks like you're new to Amazon"` appears.
* **Assertions**:

  * Login page header is correct.
  * “Not registered” message is displayed.
* **Automation Note**: Implemented in `TC01_Login.java`.

---

### **Scenario 2 – Verify items are added to cart correctly**

* Navigate to [Amazon Egypt](https://www.amazon.eg/).
* Click **Today’s Deals**.
* Select **2nd category**.
* Click **1st product** in this category.
* Select **2nd item** in this product.
* Add to cart with **quantity = 2**.
* Navigate to **Cart**.
* Verify that:

  * Product name matches the selected item.
  * Price is correct.
  * Quantity is correct.
  * Cart subtotal is correct.
* **Assertions** for each step to ensure accuracy.
* **Automation Note**: Implemented in `TC02_Cart.java`.

---

### **Scenario 3 – Verify access restrictions**

* Navigate to [Amazon Egypt](https://www.amazon.eg/).
* Hover over **"Hello, sign in Account & Lists"**.
* Select **Your Orders** → Verify user cannot see orders when not signed in.
* Select **Your Addresses** → Verify user cannot see addresses when not signed in.
* Select **Your Lists** → Verify user can see the intro screen.
* **Assertions**:

  * Orders page access blocked.
  * Addresses page access blocked.
  * Lists page displayed correctly.
* **Automation Note**: Implemented in `TC03_Access.java`.

---

## **Project Structure**

```
AmazonAutomationTask/
│
├─ src/
│  ├─ main/
│  │  └─ java/
│  │      └─ Pages/            # Page Object classes
│  │      └─ Utiles/           # Utility classes (ElementUtils, BrowserUtils, DriverManager)
│  │
│  └─ test/
│      └─ java/
│          └─ Test/            # Test classes (TC01_Login, TC02_Cart, TC03_Access)
│
├─ testng.xml                  # TestNG suite file to run all tests together
├─ pom.xml                     # Maven dependencies (Selenium, TestNG, Allure)
└─ README.md                   # Documentation
```

---

## **How the Project Was Built**

1. **Page Object Model (POM) Design**

   * Created separate **page classes** for each page:

     * `HomePage`, `LoginPage`, `DealsPage`, `ProductPage`, `CartPage`
   * All **locators and interactions** are defined in these classes.
   * Test classes focus on **test logic and assertions only**.

2. **Utility Classes**

   * `ElementUtils`: Reusable methods for clicking, sending keys, getting text, hovering.
   * `BrowserUtils`: Navigation helper methods.
   * `DriverManager`: Initializes and closes the WebDriver (Chrome) and manages driver instance.

3. **Assertions**

   * Used **TestNG assertions** for each validation:

     * `assertEquals()` for headers, text, prices.
     * `assertTrue()` for conditions like element visibility or text contains.

4. **Screenshot on Failure**

   * Implemented in `@AfterMethod` in test classes.
   * Captures **screenshot automatically** on test failure.
   * Screenshots are attached to **Allure Reports**.

5. **Allure Reporting**

   * Generates **visual reports** with:

     * Test status (pass/fail)
     * Failure screenshots
     * Logs and steps of execution
   * Tested intentionally failing test (`failureTest`) to verify screenshot capture.

---
