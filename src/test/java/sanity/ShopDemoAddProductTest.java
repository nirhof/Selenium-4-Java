package sanity;

import extensions.UIActions;
import extensions.Verifications;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;

import java.util.Arrays;
import java.util.List;

@Listeners(utilities.Listeners.class)
public class ShopDemoAddProductTest extends CommonOps {

    // Test to verify adding a number of products equals the expected count
    @Test
    public void test01_verifyAddingMultipleProducts() {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();
        Webflows.addProductAndReturnToStore(2);
        Webflows.addProductAndReturnToStore(4);
        Webflows.addProductAndReturnToStore(5);

        // go to cart page
        UIActions.mouseHover(products.btn_CartMenu);

        Verifications.numberOfElements(cartPage.ProductRow, 3); // verify number of products added equals to expected
    }

    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the URL after each test method execution

    }
}
