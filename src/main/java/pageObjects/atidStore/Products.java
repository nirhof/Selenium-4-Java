package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Products {

    @FindBy(name = "add-to-cart")
    public WebElement btn_AddToCart;

    @FindBy(linkText = "View cart")
    public WebElement btn_ViewCart;

    @FindBy(id = "ast-site-header-cart")
    public WebElement btn_CartMenu;

    @FindBy(css = "h1[class='product_title entry-title']")
    public WebElement txt_productName;

    @FindBy(css = "p[class='price']")
    public WebElement txt_productPrice;

    @FindBy(css = "input[type='number']")
    public WebElement txt_productQuantity;

}
