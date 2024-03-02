package workflows;

import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;



public class Webflows extends CommonOps {


    @Step("business flow - sort products by price Highest to Lowest")
    public static void SortProductsByPriceHighToLow() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter,"price-desc");
    }

    @Step("business flow - sort products by price Lowest to Highest")
    public static void SortProductsByPriceHLowToHigh() {
        UIActions.selectDropDownByValue(storePage.combo_OrderByFilter,"price");
    }

    @Step("business flow - search for product")
    public static void searchForProduct(String text) {
        UIActions.updateText(storePage.txt_Search, text);
        UIActions.click(storePage.btn_Submit);
    }

}



