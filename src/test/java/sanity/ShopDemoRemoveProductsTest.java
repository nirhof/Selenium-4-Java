package sanity;

import extensions.UIActions;
import extensions.Verifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

@Listeners(utilities.Listeners.class)
public class ShopDemoRemoveProductsTest extends CommonOps {

    // Test to verify removal of products from the cart
    @Test
    public void test06_verifyRemovalOfProductsFromCart() {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceHLowToHigh();

        // Adding products to cart
        Webflows.addProductAndReturnToStore(9);
        Webflows.addProductAndReturnToStore(10);
        Webflows.addProductAndReturnToStore(11);
        Webflows.addProductAndReturnToStore(12);

        driver.navigate().refresh();
        // Hover over the cart menu
        UIActions.mouseHover(products.btn_CartMenu);
        driver.navigate().refresh();
        // Remove all items from the cart
        Webflows.RemoveItem(1);

        // Verify number of products in the cart equals to expected
        Verifications.numberOfElements(cartPage.ProductRow, 3); // verify number of products added equals to expected
    }
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the URL after each test method execution

    }
}
