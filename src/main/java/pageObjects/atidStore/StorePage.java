package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StorePage {

    @FindBy(id = "wc-block-search__input-1")
    private WebElement txt_Search;
    @FindBy(css = "button[type='submit']")
    private WebElement btn_Submit;
    @FindBy(css = "a[class='woocommerce-LoopProduct-link woocommerce-loop-product__link']")
    private List<WebElement> products;
    @FindBy(css = "div[class='astra-shop-thumbnail-wrap']>a>img")
    private List<WebElement> productsImages;
    @FindBy(css = "select[name='orderby']")
    private WebElement combo_OrderByFilter;
    @FindBy(css = "span[class='woocommerce-Price-amount amount']")
    private List<WebElement> productsPrices;
    @FindBy(css = "div[class='astra-shop-summary-wrap'] > a > h2")
    private List<WebElement> productsNames;


    // Getters
    public WebElement getSearchTextBox() {
        return txt_Search;
    }

    public WebElement getSubmitButton() {
        return btn_Submit;
    }

    public List<WebElement> getProducts() {
        return products;
    }

    public List<WebElement> getProductsImages() {
        return productsImages;
    }

    public WebElement getOrderByFilterDropdown() {
        return combo_OrderByFilter;
    }

    public List<WebElement> getProductsPrices() {
        return productsPrices;
    }

    public List<WebElement> getProductsNames() {
        return productsNames;
    }
}
