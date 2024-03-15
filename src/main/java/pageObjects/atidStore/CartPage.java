package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage {

    @FindBy(css = "td[class='product-subtotal']")
    public List<WebElement> txt_ProductSubtotal;

    @FindBy(css = "input[type='number']")
    public  List<WebElement> txt_ProductQuantity;

    @FindBy(css = "td[class='product-price']")
    public  List<WebElement> txt_ProductPrice;

    @FindBy(css = "td[class='product-name']")
    public  List<WebElement> txt_ProductName;

    @FindBy(css = "tr[class='woocommerce-cart-form__cart-item cart_item']")
    public  List<WebElement> ProductRow;

    @FindBy(css = "a[class=remove]")
    public  List<WebElement> btn_RemoveProduct;

    @FindBy(css = "div[class='cart-empty woocommerce-info']")
    public  WebElement txt_cartEmpty;

    @FindBy(css = "a[class='checkout-button button alt wc-forward']")
    public  WebElement btn_Checkout;
}

