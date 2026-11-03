package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SauceProductsPage extends BasePage {
    WebDriver driver;

    public SauceProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.XPATH, using = "//span[@class='title']")
    WebElement pageTitle;

    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-backpack")
    WebElement addBackpackButton;

    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-bike-light")
    WebElement addBikeLightButton;

    @FindBy(how = How.XPATH, using = "//span[@class='shopping_cart_badge']")
    WebElement cartBadge;

    @FindBy(how = How.XPATH, using = "//a[@class='shopping_cart_link']")
    WebElement cartLink;

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void addBackpackToCart() {
        addBackpackButton.click();
    }

    public void addBikeLightToCart() {
        addBikeLightButton.click();
    }

    public String getCartCount() {
        return cartBadge.getText();
    }

    public void goToCart() {
        cartLink.click();
    }
}

