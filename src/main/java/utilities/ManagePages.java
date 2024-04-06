package utilities;
import org.openqa.selenium.support.PageFactory;



public class ManagePages extends Base {

    // Web Pages

    // Method Name : initAtidStoreDemo
    // Method Description: Initializes the various pages for the AtidStoreDemo application.
    public static void initAtidStoreDemo(){
        atidstoreHome = PageFactory.initElements(driver, pageObjects.atidStore.StoreHome.class);
        topMenu = PageFactory.initElements(driver, pageObjects.atidStore.TopMenu.class);
        storePage = PageFactory.initElements(driver, pageObjects.atidStore.StorePage.class);
        aboutPage = PageFactory.initElements(driver, pageObjects.atidStore.AboutPage.class);
        products = PageFactory.initElements(driver, pageObjects.atidStore.Products.class);
        cartPage = PageFactory.initElements(driver, pageObjects.atidStore.CartPage.class);
        checkoutPage = PageFactory.initElements(driver, pageObjects.atidStore.CheckoutPage.class);
        contactUsPage = PageFactory.initElements(driver, pageObjects.atidStore.ContactUsPage.class);


    }
}
