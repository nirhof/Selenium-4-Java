package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

public class ShopDemoCheckout extends CommonOps {

    @Test(description = "Test01 - Verify navigate to checkout page")
    @Description("This test verifies navigating to checkout page after adding a product to the cart")
    public void test01_verifyNavigateCheckout() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.getStoreButton());

        // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();

        // Add product to the cart
        Webflows.addProductAndReturnToStore(0);

        Webflows.navigateCheckoutPage();

        Verifications.verifyTextInElement(checkoutPage.getCheckoutTitle(), "Checkout");

    }

    @Test(description = "Test02 - Verify Checkout: Error Message for Invalid Payment Method")
    @Description("This test verifies the checkout process and ensures that an error message appears if there are no available payment methods")
    public void test02_verifyCheckoutProcess() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.getStoreButton());

        // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();

        // Add product to the cart
        Webflows.addProductAndReturnToStore(2);

        // Hover over the cart menu and click on the checkout button
        UIActions.mouseHover(products.getCartMenuButton());
        UIActions.click(cartPage.getCheckoutButton());

        // Perform the checkout process with provided details
        Webflows.checkout("Nir", "Cohen", "Microsoft", "Israel", "Hertzel 28", "2", "123457", "Tel Aviv", "0546900242", "nirtest@gmail.com", "add pizza cutter");

        // Verify that an error message appears if there are no available payment methods
        Verifications.verifyTextContainedInElement(checkoutPage.getErrorMessage(), "Invalid payment method.");
    }

    // Method to execute after each test method
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }

}
