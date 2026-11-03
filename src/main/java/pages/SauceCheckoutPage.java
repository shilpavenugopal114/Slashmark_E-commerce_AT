package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceCheckoutPage extends BasePage {
    WebDriver driver;

    public SauceCheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement fName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        fName.clear();
        fName.sendKeys(firstName);

        WebElement lName = driver.findElement(By.id("last-name"));
        lName.clear();
        lName.sendKeys(lastName);

        WebElement zip = driver.findElement(By.id("postal-code"));
        zip.clear();
        zip.sendKeys(postalCode);

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public String getTotalPriceText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".summary_total_label")));
        return total.getText();
    }

    public void clickFinish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finish = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finish);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finish);
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
    }

    public String getOrderConfirmationHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));
        return header.getText();
    }
}
