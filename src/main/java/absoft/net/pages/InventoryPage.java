package absoft.net.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public final By SHOPPING_CART_LINK = By.cssSelector("a.shopping_cart_link");
    private final String itemXPath = "//div[@class='inventory_item'][.//div[@class='inventory_item_name' and text()='%s']]";



    @Step ("Check Linked Shopping menu")
    public InventoryPage shouldBeSeeShoppingCartLink () {
    assertTrue(!driver.findElements(SHOPPING_CART_LINK).isEmpty()
                    && driver.findElement(SHOPPING_CART_LINK).isDisplayed(),
            "Primary header is not present");
    return this;
}
    @Step ("Adding item {itemName} to cart")
    public InventoryPage addItemToCart(String itemName) {
        WebElement item = driver.findElement(By.xpath(String.format(itemXPath, itemName)));
        WebElement addToCartButton = item.findElement(
                By.xpath("//button[contains(@data-test, 'add-to-cart')]"));
        addToCartButton.click();
        return this;
    }
    public CartPage openCart() {
        driver.findElement(SHOPPING_CART_LINK).click();
        return new CartPage(driver);
    }
}
