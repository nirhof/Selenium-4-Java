package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

public class ShopDemoAddProductTest extends CommonOps {

    @Test(description = "Test01 - Verify adding multiple products to the cart")
    @Description("This test verifies that number of products added to the cart equals to expected")
    public void test01_verifyAddingMultipleProducts() {
        // Click on the store button in the top menu
        UIActions.click(topMenu.getStoreButton());

        // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();
        Webflows.addProductAndReturnToStore(2);
        Webflows.addProductAndReturnToStore(4);
        Webflows.addProductAndReturnToStore(5);

        // go to cart page
        UIActions.mouseHover(products.getCartMenuButton());

        Verifications.numberOfElements(cartPage.getProductRows(), 3); // verify number of products added equals to expected
    }

    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
