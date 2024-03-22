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

    @FindBy(css = "input[id='billing_address_1']")
    public  WebElement txt_StreetName_HouseNumber;

    @FindBy(id = "billing_address_2")
    public  WebElement txt_Apartment;

    @FindBy(id = "select2-billing_country-container")
    public  WebElement txt_Country;

    @FindBy(id = "order_comments")
    public  WebElement txt_OrderNote;

    @FindBy(id = "place_order")
    public  WebElement btn_placeOrder;

    @FindBy(id = "billing_city")
    public  WebElement txt_town_city;

    @FindBy(css = "ul[class='woocommerce-error']")
    public  WebElement message_error;

    @FindBy(className = "select2-search__field")
    public  WebElement txt_country_search;

    @FindBy(css = "input[id='billing_state']")
    public  WebElement txt_state;

}

