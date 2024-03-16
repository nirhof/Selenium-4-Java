package sanity;

import extensions.UIActions;
import extensions.Verifications;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import utilities.PerformanceMetric;
import workflows.Webflows;

import java.util.Arrays;
import java.util.List;

@Listeners(utilities.Listeners.class)
public class ShopDemo extends CommonOps {

    // Test to verify the number of shoe products
    @Test
    public void test01_verifyNumberOfShoesProducts() {
        UIActions.click(topMenu.btn_Store); // Click on the store button in the top menu
        Webflows.searchForProduct("shoes"); // Search for shoes in the store
        Verifications.numberOfElements(storePage.products, 5); // Verify the number of shoe products is equal to expected
    }

    // Test to verify the presence of the "About Atid Store - Who We Are" text
    @Test
    public void test02_verifyAboutAtidText() {
        UIActions.click(topMenu.btn_About); // Click on the About button in the top menu
        String whoWeAreText = "ATID Demo Store was created by ATID College dedicated employees. This is a complete demo site for practicing QA & Test Automation methodologies. Don't think for a second you can actually buy here something cause you can't ! This Demo Store contains software bugs which were put intentionally and your job is to locate them Good luck folks, Yoni Flenner - ATID College";
        Verifications.verifyTextInElement(aboutPage.description.get(0), whoWeAreText); // Verify the text in the About page description
    }

    // Test to verify the anchor bracelet image
    @Test
    public void test03_verifyAnchorBraceletImage() {
        UIActions.click(topMenu.btn_Store); // Click on the store button in the top menu
        WebElement anchorBraceletImage = storePage.productsImages.get(5); // Get the anchor bracelet image element
        screenShot.createElementScreenShot(anchorBraceletImage, "anchorBraceletImage"); // Take a screenshot of the anchor bracelet image
        Verifications.visualElement("anchorBraceletImage"); // Verify the image of the product image compared to expected image
    }

    // Test to verify the highest product price
    @Test
    public void test04_verifyHighestProductPrice() {
        UIActions.click(topMenu.btn_Store); // Click on the store button in the top menu
        Webflows.SortProductsByPriceHighToLow(); // Sort the products by price from highest to lowest
        WebElement HighestPriceProduct = storePage.productsPrices.get(9); // Get the highest priced product element
        Verifications.verifyTextContainedInElement(HighestPriceProduct, "250.00 "); // Verify that the highest price product contains the expected price
    }

    // Test to verify a product added to the cart
    @Test
    public void test05_verifyDetailsOfProductAddedToCart() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceHLowToHigh();

        // Click on the product
        UIActions.click(storePage.productsImages.get(6));

        String expectedProductName = products.txt_productName.getText();
        String expectedProductPrice = Webflows.getProductPrice(products.txt_productPrice);

        // Get the element for setting quantity using relative locator
        WebElement productQuantity_txt = relativeLocator.getElement("input", "near", "name", "add-to-cart");
        String quantityValue = "3";
        // Update product quantity and add to cart
        Webflows.addProductWithQuantity(productQuantity_txt, quantityValue);

        // Hover over the cart menu and verify the product details in cart page
        UIActions.mouseHover(products.btn_CartMenu);

        // Define the list of elements representing the row
        List<WebElement> actualProdcutDetails = Arrays.asList(
                cartPage.txt_ProductName.get(0),
                cartPage.txt_ProductPrice.get(0),
                cartPage.txt_ProductQuantity.get(0),
                cartPage.txt_ProductSubtotal.get(0)
        );

        String expectedProductSubtotalPriceString = Webflows.getSubTotalPrice(expectedProductPrice, quantityValue);

        // Define the list of expected values for each element
        List<String> expectedValues = Arrays.asList(
                expectedProductName, // expected product name
                expectedProductPrice,         // expected product price
                quantityValue,         // expected Product quantity
                expectedProductSubtotalPriceString          // expected product subtotal price
        );

        // Verify details of the product added equals to the expected values
        Verifications.verifyRowDetails(actualProdcutDetails, expectedValues);
    }


    // Test to verify that the search products element is located correctly
    @Test
    public void test06_verifySearchProductsElementLocation() {
        // Click the Store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Verify the orientation of the Search textbox
        Verifications.verifyElementOrientaion(storePage.txt_Search, 198, 43, 31, 168);
    }

    // Test to verify checkout
    @Test
    public void test07_verifyCheckout() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceHLowToHigh();
        Webflows.addProductAndReturnToStore(9);
        UIActions.mouseHover(products.btn_CartMenu);
        UIActions.click(cartPage.btn_Checkout);
        Webflows.checkout("Nir","Levi","Microsoft","123123");
    }

    // Verify the performance metric for task duration
    // This test verifies whether the time taken for the task to complete, such as adding a product to the cart,
    // meets the expected threshold of 0.9 seconds.
    @Test
    public void test08_verifyPerformanceMetricTimeOfAddingAProductToCart() throws Exception {
        // Enable performance metric tracking
        performanceHandler.enableMetric();

        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceHLowToHigh();

        // Add a product to the cart and return to the store
        Webflows.addProductAndReturnToStore(9);

        // Hover over the cart menu
        UIActions.mouseHover(products.btn_CartMenu);

        // Print performance metrics
        performanceHandler.getMetricList().forEach(metric -> System.out.println(metric.getName() + " " + metric.getValue()));
        performanceHandler.printMetric(PerformanceMetric.TaskDuration.toString());

        // Verify the performance metric for task duration meets the expected threshold of 0.9 seconds.
        performanceHandler.verifyPerformanceMetricTime(PerformanceMetric.TaskDuration.toString(), 0.9);

    }

    // Method to execute after each test method
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the URL after each test method execution

    }

}
