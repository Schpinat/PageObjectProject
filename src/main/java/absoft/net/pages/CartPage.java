package absoft.net.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {

  private final String itemXPath = "//div[@class='inventory_item_name' and text()='%s']";
  private final String itemXPath2 = "//div[@class='inventory_item_name' and text()='%s']";

  public CartPage(WebDriver driver) {
    super(driver);
  }
    @FindBy(xpath = "//button[@data-test='checkout']")
    public WebElement checkout;

  @Step("Check added {itemName}")
  public CartPage checkItemPresence(String itemName) {
    String itemXPathFormatted = String.format(itemXPath, itemName);
    assertTrue(!driver.findElements(By.xpath(itemXPathFormatted)).isEmpty()
            && driver.findElement(By.xpath(itemXPathFormatted)).isDisplayed(),
        "Item " + itemName + " was not added to cart");
    return this;
  }
  @Step()
  public CartPage removeItem (String itemName) {
         WebElement item = driver.findElement(By.xpath(String.format(itemXPath2, itemName)));
      WebElement addToCartButton = item.findElement(
              By.xpath("//button[contains(@data-test, 'remove')]"));
      addToCartButton.click();
      return this;
  }

  @Step()
    public void  checkout () {
checkout.click();
  }
}
