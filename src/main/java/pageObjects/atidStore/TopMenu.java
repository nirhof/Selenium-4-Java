package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TopMenu {

    @FindBy(id = "menu-item-381")
    private WebElement btn_Home;

    @FindBy(id = "menu-item-45")
    private WebElement btn_Store;

    @FindBy(id = "menu-item-45")
    private WebElement btn_Men;

    @FindBy(id = "menu-item-828")
    private WebElement btn_About;

    @FindBy(css = "img[alt='ATID Demo Store']")
    private WebElement store_Logo;

    @FindBy(css = "a[class='button checkout wc-forward']")
    private WebElement btn_Checkout;

    @FindBy(linkText = "View cart")
    private WebElement btn_ViewCart;

    @FindBy(className = "cart-container")
    private WebElement btn_Cart_MenuContainer;

    @FindBy(className = "woocommerce-mini-cart__empty-message")
    private List<WebElement> cartEmptyIndicator;

    // Getter methods
    public WebElement getHomeButton() {
        return btn_Home;
    }

    public WebElement getStoreButton() {
        return btn_Store;
    }

    public WebElement getMenButton() {
        return btn_Men;
    }

    public WebElement getAboutButton() {
        return btn_About;
    }

    public WebElement getStoreLogo() {
        return store_Logo;
    }

    public WebElement getCheckoutButton() {
        return btn_Checkout;
    }

    public WebElement getViewCartButton() {
        return btn_ViewCart;
    }

    public WebElement getCartMenuContainer() {
        return btn_Cart_MenuContainer;
    }

    public List<WebElement> getCartEmptyIndicator() {
        return cartEmptyIndicator;
    }

}
