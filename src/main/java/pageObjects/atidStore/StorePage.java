package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StorePage {

    @FindBy(id = "wc-block-search__input-1")
    public WebElement txt_Search;
    @FindBy(css = "button[type='submit']")
    public WebElement btn_Submit;
    @FindBy(css = "a[class='woocommerce-LoopProduct-link woocommerce-loop-product__link']")
    public List<WebElement> products;
    @FindBy(css = "img[class='attachment-woocommerce_thumbnail size-woocommerce_thumbnail']")
    public List<WebElement> productsImages;
    @FindBy(css = "select[name='orderby']")
    public WebElement combo_OrderByFilter;
    @FindBy(css = "span[class='woocommerce-Price-amount amount']")
    public List<WebElement> productsPrices;
}
