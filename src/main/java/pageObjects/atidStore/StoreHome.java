package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreHome {

    @FindBy(linkText = "Home")
    private WebElement btn_Home;

    @FindBy(id = "ap_email")
    private WebElement txt_Email;

    // Getters
    public WebElement getHomeButton() {
        return btn_Home;
    }

    public WebElement getEmailTextField() {
        return txt_Email;
    }

}
