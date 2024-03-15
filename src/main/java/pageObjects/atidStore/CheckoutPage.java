package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage {

    @FindBy(id = "billing_first_name")
    public  WebElement txt_first_name;

    @FindBy(id = "billing_last_name")
    public  WebElement txt_last_name;

}

