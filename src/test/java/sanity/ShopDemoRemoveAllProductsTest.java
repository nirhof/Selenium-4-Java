package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

public class ShopDemoRemoveAllProductsTest extends CommonOps {

    @Test(description = "Test01 - Verify removal of all products from the cart")
    @Description("This test verifies the functionality of removing all products from the cart.")
    public void test01_verifyRemovalOfAllProductsFromCart() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.getStoreButton());

    // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();
        Webflows.addProductAndReturnToStore(2);
        Webflows.addProductAndReturnToStore(4, "3");
        Webflows.addProductAndReturnToStore(5, "2");

        // go to cart page
        UIActions.mouseHover(products.getCartMenuButton());

        // Remove all items from the cart
        Webflows.RemoveAllItem(cartPage.getProductRows());

        // Verify that the cart is empty
        Verifications.verifyTextInElement(cartPage.getCartEmptyText(), "Your cart is currently empty.");
    }
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
