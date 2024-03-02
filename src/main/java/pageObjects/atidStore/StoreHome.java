package pageObjects.atidStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreHome {

    @FindBy(linkText = "Home")
    public WebElement btn_Home;

    @FindBy(id = "ap_email")
    public WebElement txt_Email;







}
