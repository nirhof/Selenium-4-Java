package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Products {

    @FindBy(name = "add-to-cart")
    private WebElement btn_AddToCart;

    @FindBy(id = "ast-site-header-cart")
    private WebElement btn_CartMenu;

    @FindBy(css = "h1[class='product_title entry-title']")
    private WebElement txt_productName;

    @FindBy(css = "p[class='price']")
    private WebElement txt_productPrice;

    @FindBy(css = "input[type='number']")
    private WebElement txt_productQuantity;

    @FindBy(css = "p[class='stock out-of-stock'")
    private List <WebElement> outOfStockIndicator;


    // Getters
    public WebElement getAddToCartButton() {
        return btn_AddToCart;
    }

    public WebElement getCartMenuButton() {
        return btn_CartMenu;
    }

    public WebElement getProductName() {
        return txt_productName;
    }

    public WebElement getProductPrice() {
        return txt_productPrice;
    }

    public WebElement getProductQuantityField() {
        return txt_productQuantity;
    }

    public List<WebElement> getOutOfStockIndicator() {
        return outOfStockIndicator;
    }

}
