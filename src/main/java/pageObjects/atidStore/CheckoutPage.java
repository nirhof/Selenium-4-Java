package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage {

    @FindBy(id = "billing_first_name")
    public  WebElement txt_first_name;

    @FindBy(id = "billing_last_name")
    public  WebElement txt_last_name;

    @FindBy(id = "billing_company")
    public  WebElement txt_company_name;

    @FindBy(id = "billing_postcode")
    public  WebElement txt_postcode;

    @FindBy(id = "billing_city")
    public  WebElement txt_city;

    @FindBy(id = "billing_phone")
    public  WebElement txt_phone;

    @FindBy(id = "billing_email")
    public  WebElement txt_email;

}

