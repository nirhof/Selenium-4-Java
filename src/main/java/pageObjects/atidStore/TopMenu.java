package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TopMenu {

    @FindBy(id = "menu-item-381")
    public WebElement btn_Home;

    @FindBy(id = "menu-item-45")
    public WebElement btn_Store;

    @FindBy(id = "menu-item-45")
    public WebElement btn_Men;

    @FindBy(id = "menu-item-828")
    public WebElement btn_About;

    @FindBy(css = "img[alt='ATID Demo Store']")
    public WebElement store_Logo;

    @FindBy(css = "a[class='button checkout wc-forward']")
    public WebElement btn_Checkout;

    @FindBy(linkText = "View cart")
    public WebElement btn_ViewCart;

    @FindBy(className = "cart-container")
    public WebElement btn_Cart_MenuContainer;

    @FindBy(className = "woocommerce-mini-cart__empty-message")
    public List<WebElement> cartEmptyIndicator;


}
