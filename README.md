# Task 1 – Automation Testing for E-Commerce Websites

**SlashMark Internship Project Submission**

## 📌 Project Overview
This project is an end-to-end Test Automation Framework developed for an e-commerce platform using **Selenium WebDriver (Java)**, **TestNG**, and the **Page Object Model (POM)** design pattern. It automates key customer journeys—including authentication, product browsing, adding items to the cart, checkout flows, payment verification, and order placement.

---

## 🛠 Tech Stack & Tools
* **Language:** Java (JDK 17)
* **Automation Library:** Selenium WebDriver 4.21.0 (with automated Selenium Manager)
* **Test Runner & Assertions:** TestNG 7.10.2
* **Design Pattern:** Page Object Model (POM)
* **Build & Dependency Management:** Apache Maven
* **Data-Driven Testing:** Apache POI (Excel integration)
* **Target E-Commerce Application:** [SauceDemo (Swag Labs)](https://www.saucedemo.com/)

---

## 🏗 Architecture & Project Structure
```text
TestAutomationEcommerceSite/
├── data/
│   └── testdata.xlsx                    # Test data sheets
├── src/
│   ├── main/java/
│   │   ├── pages/                       # Page Object classes (POM)
│   │   │   ├── BasePage.java            # Common utilities and explicit waits
│   │   │   ├── SauceLoginPage.java      # Login page locators & actions
│   │   │   ├── SauceProductsPage.java   # Products catalog & add-to-cart
│   │   │   ├── SauceCartPage.java       # Cart verification & checkout trigger
│   │   │   └── SauceCheckoutPage.java   # Shipping info, overview & finish order
│   │   └── util/
│   │       ├── BrowserFactory.java      # Browser initialization & management
│   │       └── ExcelReader.java         # Apache POI Excel reader
│   └── test/java/
│       └── tests/
│           └── SauceDemoEcommerceTest.java # Test suites & assertions
├── test-output/                         # HTML execution reports
├── pom.xml                              # Maven build & dependencies
└── testng.xml                           # TestNG suite execution configuration
```

---

## 🧪 Test Scenarios Covered
| Test ID | Scenario | Verification / Assertion | Status |
| :--- | :--- | :--- | :---: |
| **TC-01** | **User Login (Positive)** | Verifies valid login redirects user to Products catalog | `PASSED` |
| **TC-02** | **User Login (Negative)** | Verifies appropriate error notification appears for invalid login | `PASSED` |
| **TC-03** | **Browse & Add to Cart** | Verifies item addition updates shopping cart badge count | `PASSED` |
| **TC-04** | **End-to-End Buy Order** | Complete checkout flow: Add to Cart → Shipping Info → Total Price Calculation → Order Placement Confirmation ("Thank you for your order!") | `PASSED` |

---

## 🚀 How to Run the Tests

### Option 1: In VS Code
1. Open the project folder in VS Code.
2. In the Explorer, navigate to:
   `src/test/java/tests/SauceDemoEcommerceTest.java`
3. Click the green **Run Test** button above the class name or above any individual `@Test` method.

### Option 2: Using TestNG Suite File
* Right-click `testng.xml` and select **Run As > TestNG Suite**.

---

## 📊 Test Execution Report
After execution, detailed HTML reports are generated under the `test-output/` folder:
* **`test-output/emailable-report.html`** – Clean summary report with pass/fail counts and timings.
* **`test-output/index.html`** – Detailed report with step-by-step logs.
