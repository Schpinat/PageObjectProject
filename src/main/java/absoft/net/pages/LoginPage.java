package absoft.net.pages;

import absoft.net.data.Account;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "input[data-test='username']")
    public WebElement usernameInput;

    @FindBy(css = "input[data-test='password']")
    public WebElement passwordInput;

    @FindBy(css = "input[data-test='login-button']")
    public WebElement loginButton;

    @Step ("Logging")
    public void loginBy (Account account) {

        usernameInput.sendKeys(account.getLogin());
        passwordInput.sendKeys(account.getPassword());
        loginButton.click();

    }
}

