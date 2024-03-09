package workflows;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

import java.util.List;


public class Webflows extends CommonOps {


    @Step("business flow - sort products by price Highest to Lowest")
    public static void SortProductsByPriceHighToLow() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter, "price-desc");
    }

    @Step("business flow - sort products by price Lowest to Highest")
    public static void SortProductsByPriceHLowToHigh() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter, "price");
    }

    @Step("business flow - search for product")
    public static void searchForProduct(String text) {
        UIActions.updateText(storePage.txt_Search, text);
        UIActions.click(storePage.btn_Submit);
    }

    @Step("business flow - Remove all Items from cart")
    public static void RemoveAllItem(List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            UIActions.click(elements.get(i));
        }
    }

    @Step("business flow - get product price")
    public static String getProductPrice(WebElement productPrice) {
        String productPriceText = productPrice.getText();
        // Count the number of times "₪" appears in the text
        int count = productPriceText.length() - productPriceText.replace("₪", "").length();

        if (count == 1) {
            return productPriceText; // Return the input string if "₪" appears only once
        } else if (count == 2) {
            String[] prices = productPriceText.split("₪");
            String price1 = prices[0].trim();
            String price2 = prices[1].trim();
            // Compare prices as strings
            if (price1.compareTo(price2) < 0) {
                return price1 + " ₪"; // Concatenate ".00" to the smaller price
            } else {
                return price2 + " ₪"; // Concatenate ".00" to the smaller price
            }
        }
        return "Invalid input: More than two prices found";
    }

    @Step("business flow - get product subtotal price")
    public static String getSubTotalPrice(String productPrice, String quantityValue) {
        String[] parts = productPrice.split(" "); // Split the string by space
        String priceString = parts[0]; // Get the first part, which should be the numerical value
        priceString = priceString.replaceAll("[^0-9.]+", ""); // Remove non-numeric characters except for the decimal point
        double price = Double.parseDouble(priceString); // Convert the extracted numerical part to an integer
        int quantity = Integer.parseInt(quantityValue);
        double productSubtotalPrice = price * quantity; // Calculate subtotal price as a double

        // Format the productSubtotalPrice to have two decimal places and append " ₪"
        return productSubtotalPrice+ "0 ₪";
    }

    @Step("business flow - choose quantity and add product tp the cart")
    public static void addProductWithQuantity(WebElement element,String quantityValue) {
        UIActions.updateText(element, quantityValue);
        UIActions.click(products.btn_AddToCart);
    }

    @Step("business flow - add product tp the cart")
    public static void addProductAndReturnToStore(int productIndex, String quantityValue)  {
        UIActions.click(storePage.productsImages.get(productIndex));
        UIActions.updateText(products.txt_productQuantity, quantityValue);
        UIActions.click(products.btn_AddToCart);
        driver.navigate().back();
        driver.navigate().back();
    }

    @Step("business flow - add product tp the cart")
    public static void addProductAndReturnToStore(int productIndex)  {
        UIActions.click(storePage.productsImages.get(productIndex));
        UIActions.click(products.btn_AddToCart);
        driver.navigate().back();
        driver.navigate().back();
    }
}

