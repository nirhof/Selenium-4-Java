package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage {

    @FindBy(css = "td[class='product-subtotal']")
    private List<WebElement> txt_ProductSubtotal;

    @FindBy(css = "input[type='number']")
    private List<WebElement> txt_ProductQuantity;

    @FindBy(css = "td[class='product-price']")
    private List<WebElement> txt_ProductPrice;

    @FindBy(css = "td[class='product-name']")
    private List<WebElement> txt_ProductName;

    @FindBy(css = "tr[class='woocommerce-cart-form__cart-item cart_item']")
    private List<WebElement> productRow;

    @FindBy(css = "a[class=remove]")
    private List<WebElement> btn_RemoveProduct;

    @FindBy(css = "div[class='cart-empty woocommerce-info']")
    private WebElement txt_cartEmpty;

    @FindBy(css = "a[class='checkout-button button alt wc-forward']")
    private WebElement btn_Checkout;

    // Getters
    public List<WebElement> getProductSubtotals() {
        return txt_ProductSubtotal;
    }

    public List<WebElement> getProductQuantities() {
        return txt_ProductQuantity;
    }

    public List<WebElement> getProductPrices() {
        return txt_ProductPrice;
    }

    public List<WebElement> getProductNames() {
        return txt_ProductName;
    }

    public List<WebElement> getProductRows() {
        return productRow;
    }

    public List<WebElement> getRemoveProductButtons() {
        return btn_RemoveProduct;
    }

    public WebElement getCartEmptyText() {
        return txt_cartEmpty;
    }

    public WebElement getCheckoutButton() {
        return btn_Checkout;
    }
}

