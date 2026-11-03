package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SauceCartPage;
import pages.SauceCheckoutPage;
import pages.SauceLoginPage;
import pages.SauceProductsPage;
import util.BrowserFactory;

/**
 * Task 1: Automation Testing for E-Commerce Websites
 * Key Flows Automated:
 * 1. User Authentication (Positive & Negative login validation)
 * 2. Product Catalog Browsing
 * 3. Add to Cart flow
 * 4. End-to-End Checkout, Price Verification & Order Payment Confirmation
 */
public class SauceDemoEcommerceTest {

    WebDriver driver;
    private final String BASE_URL = "https://www.saucedemo.com";

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.LaunchBrowser(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        try {
            // Keep browser open for 5 seconds before closing
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BrowserFactory.CloseBrowser();
    }

    @Test(priority = 1, description = "Verify successful user login with valid credentials")
    public void testSuccessfulLogin() throws InterruptedException {
        SauceLoginPage loginPage = PageFactory.initElements(driver, SauceLoginPage.class);
        loginPage.login("standard_user", "secret_sauce");

        // Pause 5 seconds to observe successful login and products page
        Thread.sleep(5000);

        SauceProductsPage productsPage = PageFactory.initElements(driver, SauceProductsPage.class);
        Assert.assertEquals(productsPage.getPageTitle(), "Products", "User should be redirected to Products catalog");
    }

    @Test(priority = 2, description = "Verify error message appears with invalid credentials")
    public void testInvalidLoginShowsError() throws InterruptedException {
        SauceLoginPage loginPage = PageFactory.initElements(driver, SauceLoginPage.class);
        loginPage.login("locked_out_user", "wrong_password");

        // Pause 5 seconds to observe the red error notification
        Thread.sleep(5000);

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match") || error.contains("Epic sadface"),
                "Error banner should be displayed for invalid credentials");
    }

    @Test(priority = 3, description = "Verify browsing products and adding items to cart")
    public void testBrowseAndAddToCart() throws InterruptedException {
        SauceLoginPage loginPage = PageFactory.initElements(driver, SauceLoginPage.class);
        loginPage.login("standard_user", "secret_sauce");

        SauceProductsPage productsPage = PageFactory.initElements(driver, SauceProductsPage.class);
        productsPage.addBackpackToCart();

        // Pause 5 seconds to observe item added and cart badge counter (1)
        Thread.sleep(5000);

        Assert.assertEquals(productsPage.getCartCount(), "1", "Cart badge should display 1 item added");
    }

    @Test(priority = 4, description = "Automate End-to-End Buy Order (Browse -> Add to Cart -> Checkout -> Payment -> Order Placed)")
    public void testEndToEndBuyOrderFunctionality() throws InterruptedException {
        // Step 1: Login
        SauceLoginPage loginPage = PageFactory.initElements(driver, SauceLoginPage.class);
        loginPage.login("standard_user", "secret_sauce");
        System.out.println("[STEP 1] Logged in successfully. Pausing 5 seconds...");
        Thread.sleep(5000);

        // Step 2: Browse and Add product to cart
        SauceProductsPage productsPage = PageFactory.initElements(driver, SauceProductsPage.class);
        productsPage.addBackpackToCart();
        System.out.println("[STEP 2] Added item to cart. Opening cart...");
        productsPage.goToCart();
        Thread.sleep(5000);

        // Step 3: Cart verification & proceed to checkout
        SauceCartPage cartPage = PageFactory.initElements(driver, SauceCartPage.class);
        Assert.assertEquals(cartPage.getItemName(), "Sauce Labs Backpack", "Selected item should match item in cart");
        System.out.println("[STEP 3] Verified item in cart. Clicking checkout...");
        cartPage.clickCheckout();
        Thread.sleep(5000);

        // Step 4: Enter Checkout Information (Name, Address, Zip)
        SauceCheckoutPage checkoutPage = PageFactory.initElements(driver, SauceCheckoutPage.class);
        System.out.println("[STEP 4] Entering shipping information and continuing...");
        checkoutPage.enterShippingInformation("John", "Doe", "75001");
        Thread.sleep(5000);

        // Step 5: Verify total price and finish order
        String totalPrice = checkoutPage.getTotalPriceText();
        System.out.println("[STEP 5] Total Price verified: " + totalPrice + ". Completing order...");
        Assert.assertTrue(totalPrice.contains("$"), "Total price should be displayed with currency symbol");
        checkoutPage.clickFinish();
        Thread.sleep(5000);

        // Step 6: Assert order was successfully completed
        String confirmationMessage = checkoutPage.getOrderConfirmationHeader();
        System.out.println("[STEP 6] Order Completed! Confirmation: " + confirmationMessage);
        Assert.assertEquals(confirmationMessage, "Thank you for your order!",
                "Order confirmation header must be displayed upon successful checkout");
    }
}
