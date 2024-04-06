package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;
import java.util.Arrays;
import java.util.List;

@Listeners(utilities.Listeners.class)
public class ShopDemo extends CommonOps {

    // Test to verify the search for a specific product
    @Test(description = "Test01 - Verify search for a specific product",dataProvider = "specificProductData", dataProviderClass = utilities.ManageDDT.class)
    @Description("This test verifies the search process of specific product")
    public void test01_verifySearchForSpecificProduct(String productName, String expectedProductName) {

        UIActions.click(topMenu.btn_Store); // Clicks on the store button in the top menu
        Webflows.searchForProduct(productName); // Initiates a search for the specified product in the store
        Verifications.verifyTextInElement(products.txt_productName, expectedProductName); // Verifies the name of the product
    }

    // Test to search for a keyword and validate the number of products in the results
    @Test(description = "Test02 - Verify search for a keyword and validate the number of products in the results", dataProvider = "searchKeywordData", dataProviderClass = utilities.ManageDDT.class)
    @Description("This test verifies the number of products obtained after searching for a keyword.")
    public void test02_SearchForKeywordAndVerifyProductsCount(String searchKey, String numberOfProducts) {
        UIActions.click(topMenu.btn_Store); // Clicks on the store button in the top menu
        Webflows.searchForProduct(searchKey); // Searches for the keyword in the store
        int expectedResult = Integer.parseInt(numberOfProducts);
        Verifications.numberOfElements(storePage.products, expectedResult); // Verifies that the number of products matches the expected value
    }

    // Test to verify the text of the "About Atid Store - Who We Are"
    @Test(description = "Test03 - Verify About Atid Text")
    @Description("This test verifies the text of the 'About Atid Store - Who We Are'")
    public void test03_verifyAboutAtidText() {
        UIActions.click(topMenu.btn_About); // Click on the About button in the top menu
        String whoWeAreText = "ATID Demo Store was created by ATID College dedicated employees. This is a complete demo site for practicing QA & Test Automation methodologies. Don't think for a second you can actually buy here something cause you can't ! This Demo Store contains software bugs which were put intentionally and your job is to locate them Good luck folks, Yoni Flenner - ATID College";
        Verifications.verifyTextInElement(aboutPage.description.get(0), whoWeAreText); // Verify the text in the About page description
    }

    // Test to verify the product image
    @Test(description = "Test04 - Verify product Image")
    @Description("This test verifies the presence of product image")
    public void test04_verifyProductImage() {
        UIActions.click(topMenu.btn_Store); // Click on the store button in the top menu
        WebElement anchorBraceletImage = storePage.productsImages.get(0); // Get the product image element
        screenShot.createElementScreenShot(anchorBraceletImage, "anchorBraceletImage"); // Take a screenshot of the product image
        Verifications.visualElement("anchorBraceletImage"); // Verify the image of the product image compared to expected image
    }

    // Test to verify the highest product price
    @Test(description = "Test05 - Verify Highest Product Price")
    @Description("This test verifies the highest product price")
    public void test05_verifyHighestProductPrice() {
        UIActions.click(topMenu.btn_Store); // Click on the store button in the top menu
        Webflows.SortProductsByPriceHighToLow(); // Sort the products by price from highest to lowest
        WebElement HighestPriceProduct = storePage.productsPrices.get(9); // Get the highest priced product element
        Verifications.verifyTextContainedInElement(HighestPriceProduct, "260.00 "); // Verify that the highest price product contains the expected price
    }

    // Test to verify a product added to the cart and verify the product details
    @Test(description = "Test06 - Verify Details Of Product Added To Cart")
    @Description("This test verifies the details of a product added to the cart")
    public void test06_verifyDetailsOfProductAddedToCart() throws Exception {
        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from lowest to highest
        Webflows.SortProductsByPriceLowToHigh();

        // Click on the product
        UIActions.click(storePage.productsImages.get(6));

        String expectedProductName = products.txt_productName.getText();
        String expectedProductPrice = Webflows.getProductPrice(products.txt_productPrice);

        // Get the element for setting quantity using relative locator
        WebElement productQuantity_txt = relativeLocator.getElement("input", "near", "name", "add-to-cart");
        String quantityValue = "3";
        // Update product quantity and add to cart
        Webflows.addProductWithQuantity(productQuantity_txt, quantityValue);

        // go to cart page
        UIActions.mouseHover(products.btn_CartMenu);

        // Get the product details in cart page and define the list actual product details : product name, product price, quantity, product subtotal price
        List<WebElement> actualProdcutDetails = Arrays.asList(
                cartPage.txt_ProductName.get(0),
                cartPage.txt_ProductPrice.get(0),
                cartPage.txt_ProductQuantity.get(0),
                cartPage.txt_ProductSubtotal.get(0)
        );

        String expectedProductSubtotalPriceString = Webflows.getSubTotalPrice(expectedProductPrice, quantityValue);

        // Define the list expected product details : product name, product price, quantity, product subtotal price
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
    @Test(description = "Test07 - Verify Search Products Element Location")
    @Description("This test verifies the location of the search products element")
    public void test07_verifySearchProductsElementLocation() {
        // Click the Store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Verify the orientation of the Search textbox
        Verifications.verifyElementOrientaion(storePage.txt_Search, 198, 43, 31, 168);
    }

    // Method to execute after each test method
    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }

}
