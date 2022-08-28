package absoft.net;

import absoft.net.data.Account;
import absoft.net.data.UserData;
import absoft.net.pages.CartPage;
import absoft.net.pages.Checkout;
import absoft.net.pages.InventoryPage;
import absoft.net.pages.LoginPage;
import io.qameta.allure.Feature;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSuite extends BaseTest {

    private final String ITEM_1 = "Sauce Labs Backpack";
    private final String ITEM_2 = "Sauce Labs Bike Light";
    Account account = Account.STANDARD_USER;
    UserData userData = UserData.DATA_USER;

    @Feature("The user can make a purchase AND the deleted product does not appear in the shopping cart during payment")
    @Test
    public void testCustomerSuccessfulPurchase () {
        new LoginPage(driver).loginBy(account);
        new InventoryPage(driver).shouldBeSeeShoppingCartLink();
        new InventoryPage(driver).addItemToCart(ITEM_1);
        new InventoryPage(driver).addItemToCart(ITEM_2);
        new InventoryPage(driver).openCart();
        new InventoryPage(driver).shouldBeSeeShoppingCartLink();
        new CartPage(driver).checkItemPresence(ITEM_1);
        new CartPage(driver).checkItemPresence(ITEM_2);
        new CartPage(driver).removeItem(ITEM_2);
        new CartPage(driver).checkout();
        new InventoryPage(driver).shouldBeSeeShoppingCartLink();
        new Checkout(driver).inputCheckoutData(userData);
        new Checkout(driver).shouldNotItemInCart(ITEM_2);
        new Checkout(driver).finish();
        new Checkout(driver).checkFinish();

    }

}
