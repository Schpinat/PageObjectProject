package absoft.net.pages;

import absoft.net.data.UserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class Checkout extends BasePage {
    public Checkout(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@data-test='firstName']")
    public WebElement firstName;
    @FindBy(xpath = "//input[@data-test='lastName']")
    public WebElement lastName;
    @FindBy(xpath = "//input[@data-test='postalCode']")
    public WebElement postalCode;
    @FindBy(xpath = "//input[@data-test='continue']")
    public WebElement submit;
    @FindBy(xpath = "//button[@data-test='finish']")
    public WebElement finish;
    private final String itemXPath3 = "//div[@class='inventory_item_name' and text()='%s']";

    @Step("Input data in checkout")
    public void inputCheckoutData(UserData userData) {
        firstName.sendKeys(userData.getFirstName());
        lastName.sendKeys(userData.getLastName());
        postalCode.sendKeys(userData.getPostalCode());
        submit.click();

    }

    @Step("No item in checkout")
    public Checkout shouldNotItemInCart(String itemName) {
      assertTrue(  driver.findElement(By.xpath(String.format(itemXPath3, itemName))).isDisplayed());
               return this;
    }
    @Step("Finish it")
    public void finish () {
        finish.click();
    }
    @Step
    public Checkout checkFinish () {
        assertTrue(driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]")).isDisplayed());
    return this;
    }

}
