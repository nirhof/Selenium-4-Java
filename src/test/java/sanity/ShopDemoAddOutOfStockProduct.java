package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;



public class ShopDemoAddOutOfStockProduct extends CommonOps {

    @Test(description = "Test01 - Verify adding an out-of-stock product")
    @Description("This test verifies that adding an out-of-stock product does not add it to the cart.")
    public void test01_verifyAddingOutOfStockProduct() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.getStoreButton());

        // Sort the products by the latest added to store
        Webflows.SortProductsByLatest();

        // Add an out-of-stock product and return to the store page
        Webflows.addProductAndReturnToStore(2, "3");

        // Go to the cart page
        UIActions.mouseHover(products.getCartMenuButton());

        // Verify that the cart is empty
        Verifications.verifyTextInElement(cartPage.getCartEmptyText(), "Your cart is currently empty.");
    }

    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
