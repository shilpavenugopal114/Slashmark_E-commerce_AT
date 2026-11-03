package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceCartPage extends BasePage {
    WebDriver driver;

    public SauceCartPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.CLASS_NAME, using = "inventory_item_name")
    WebElement cartItemName;

    @FindBy(how = How.ID, using = "checkout")
    WebElement checkoutButton;

    public String getItemName() {
        return cartItemName.getText();
    }

    public void clickCheckout() {
        checkoutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
}
