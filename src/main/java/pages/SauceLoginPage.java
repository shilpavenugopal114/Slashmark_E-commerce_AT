package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SauceLoginPage extends BasePage {
    WebDriver driver;

    public SauceLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.ID, using = "user-name")
    WebElement usernameField;

    @FindBy(how = How.ID, using = "password")
    WebElement passwordField;

    @FindBy(how = How.ID, using = "login-button")
    WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//h3[@data-test='error']")
    WebElement errorMessage;

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}

