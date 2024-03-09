package sanity;

import extensions.UIActions;
import extensions.Verifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
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
    public void test03_verifyanchorBraceletImage() {
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

    // Test to verify deletion of products from the cart
    @Test
    public void test06_verifyDeletionOfProductsFromCart() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceHLowToHigh();

        // Click on the product
        UIActions.click(storePage.productsImages.get(5));

        // Get the element for setting quantity using relative locator
        WebElement productQuantity_txt = relativeLocator.getElement("input", "near", "name", "add-to-cart");

        // Define the quantity of the product to be added to the cart
        String quantityValue = "3";
        // Update product quantity and add to cart
        Webflows.addProductWithQuantity(productQuantity_txt, quantityValue);

        // Hover over the cart menu and verify the product details in the cart page
        UIActions.mouseHover(products.btn_CartMenu);

        // Remove all items from the cart
        Webflows.RemoveAllItem(cartPage.btn_RemoveProduct);

        // Verify that the cart is empty
        Verifications.verifyTextInElement(cartPage.txt_cartEmpty, "Your cart is currently empty.");
    }

    // Method to execute after each test method
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the URL after each test method execution
    }
}
