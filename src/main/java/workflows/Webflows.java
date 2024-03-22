package workflows;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonOps;

import java.util.List;


public class Webflows extends CommonOps {


    @Step("business flow - sort products by price Highest to Lowest")
    public static void SortProductsByPriceHighToLow() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter, "price-desc");
    }

    @Step("business flow - sort products by price Lowest to Highest")
    public static void SortProductsByPriceLowToHigh() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter, "price");
    }

    @Step("business flow - sort products by latest ")
    public static void SortProductsByLatest() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter, "date");
    }

    @Step("business flow - search for product")
    public static void searchForProduct(String text) {
        UIActions.updateText(storePage.txt_Search, text);
        UIActions.click(storePage.btn_Submit);
    }

    @Step("business flow - Remove all Items from cart")
    public static void RemoveAllItem(List<WebElement> products) {
        int length = products.size();
        for (int i = 0; i < length; i++) {
            UIActions.click(cartPage.btn_RemoveProduct.get(0));
            wait.until(ExpectedConditions.stalenessOf(cartPage.btn_RemoveProduct.get(0)));
        }
    }

    // Method to remove item from cart
    @Step("business flow - Remove item from cart")
    public static void RemoveItem(int productNumber) {
        List<WebElement> productRow = cartPage.ProductRow; // Get the current state of ProductRow
        int length = productRow.size();
        if (productNumber < 0 || productNumber >= length) {
            throw new IllegalArgumentException("Invalid product number. It should be within the range [0, " + (length - 1) + "]");
        }
        UIActions.click(cartPage.btn_RemoveProduct.get(productNumber));
        wait.until(ExpectedConditions.stalenessOf(cartPage.btn_RemoveProduct.get(productNumber)));
    }

    @Step("business flow - get product price")
    public static String getProductPrice(WebElement productPrice) {
        String productPriceText = productPrice.getText();
        // Count the number of times "₪" appears in the text
        int count = 0;

        for (int i = 0; i < productPriceText.length(); i++) {
            if (productPriceText.charAt(i) == '₪') {
                count++;
            }
        }

        if (count == 1) {
            return productPriceText; // Return the input string if "₪" appears only once

        } else if (count == 2) {
            String[] prices = productPriceText.split("₪");
            // Assuming the first price is the regular price and the second one is the sale price
            String salePrice = prices[1].trim(); // Sale Price
            return salePrice + " ₪"; // Return the sale price with currency symbol
        }
        return "Invalid input: More than two prices found";
    }

    @Step("business flow - get product subtotal price")
    public static String getSubTotalPrice(String productPrice, String quantityValue) {
        // Remove all characters from the productPrice string except for digits (0-9) and the decimal point (.)
        double price = Double.parseDouble(productPrice.replaceAll("[^0-9.]+", ""));

        // Convert quantityValue string to an integer
        int quantity = Integer.parseInt(quantityValue);

        // Calculate the subtotal price by multiplying the price by the quantity
        double productSubtotalPrice = price * quantity;

        // Append "0 ₪" directly to the subtotal price and return as a string
        return productSubtotalPrice + "0 ₪";
    }

    @Step("business flow - choose quantity and add product to the cart")
    public static void addProductWithQuantity(WebElement element, String quantityValue) {
        UIActions.updateText(element, quantityValue);
        UIActions.click(products.btn_AddToCart);
    }

    @Step("business flow - add product to the cart (choose product quantity)")
    public static void addProductAndReturnToStore(int productIndex, String quantityValue) throws Exception {
        WebElement productElement = storePage.productsImages.get(productIndex);
        UIActions.click(productElement);
        List<WebElement> outOfStockIndicator = products.outOfStockIndicator; // Get the current state of ProductRow

        if (outOfStockIndicator.size() > 0) {
            System.out.println("Product is out of stock. back to store");
            driver.navigate().back();
        } else {
            UIActions.updateText(products.txt_productQuantity, quantityValue);
            UIActions.click(products.btn_AddToCart);
            // Navigate back to store
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    @Step("business flow - add product to the cart - default quantity")
    public static void addProductAndReturnToStore(int productIndex) {
        WebElement productElement = storePage.productsImages.get(productIndex);
        UIActions.click(productElement);
        List<WebElement> outOfStockIndicator = products.outOfStockIndicator; // Get the current state of ProductRow

        if (outOfStockIndicator.size() > 0) {
            System.out.println("Product is out of stock. back to store");
            driver.navigate().back();
        } else {
            UIActions.click(products.btn_AddToCart);
            // Navigate back to store
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    @Step("business flow - checkout")
    public static void checkout(String firstName, String lastName, String companyName, String postCode) {
        UIActions.updateText(checoutPage.txt_first_name, firstName);
        UIActions.updateText(checoutPage.txt_last_name, lastName);
        UIActions.updateText(checoutPage.txt_company_name, companyName);
        UIActions.updateText(checoutPage.txt_postcode, postCode);
        UIActions.updateText(checoutPage.txt_phone, "0546900242");
        UIActions.updateText(checoutPage.txt_email, "nir108@gmail.com");
    }
}

