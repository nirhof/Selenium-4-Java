package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

public class ShopDemoRemoveProductsTest extends CommonOps {

    @Test(description = "Test01 - Verify removal of products from the cart")
    @Description("This test verifies the functionality of removing products from the cart.")
    public void test01_verifyRemovalOfProductsFromCart() {
        // Click on the store button in the top menu
        UIActions.click(topMenu.getStoreButton());

        // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();

        // Adding products to cart
        Webflows.addProductAndReturnToStore(2);
        Webflows.addProductAndReturnToStore(4);
        Webflows.addProductAndReturnToStore(5);
        Webflows.addProductAndReturnToStore(6);

        // go to cart page
        UIActions.mouseHover(products.getCartMenuButton());

        // Remove items from the cart
        Webflows.RemoveItem(3);
        Webflows.RemoveItem(0);

        // Verify number of products in the cart equals to expected
        Verifications.numberOfElements(cartPage.getProductRows(), 2); // verify number of products added equals to expected
    }
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
