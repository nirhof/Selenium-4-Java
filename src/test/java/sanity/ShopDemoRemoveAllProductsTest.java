package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

@Listeners(utilities.Listeners.class)
public class ShopDemoRemoveAllProductsTest extends CommonOps {

    // Test to verify removal of all products from the cart
    @Test(description = "Test01 - Verify removal of all products from the cart")
    @Description("This test verifies the functionality of removing all products from the cart.")
    public void test01_verifyRemovalOfAllProductsFromCart() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

    // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();
        Webflows.addProductAndReturnToStore(2);
        Webflows.addProductAndReturnToStore(4, "3");
        Webflows.addProductAndReturnToStore(5, "2");

        // go to cart page
        UIActions.mouseHover(products.btn_CartMenu);

        // Remove all items from the cart
        Webflows.RemoveAllItem(cartPage.ProductRow);

        // Verify that the cart is empty
        Verifications.verifyTextInElement(cartPage.txt_cartEmpty, "Your cart is currently empty.");
    }
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
