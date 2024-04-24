package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage {

    @FindBy(id = "billing_first_name")
    private WebElement txt_first_name;

    @FindBy(id = "billing_last_name")
    private WebElement txt_last_name;

    @FindBy(id = "billing_company")
    private WebElement txt_company_name;

    @FindBy(id = "billing_postcode")
    private WebElement txt_postcode;

    @FindBy(id = "billing_city")
    private WebElement txt_city;

    @FindBy(id = "billing_phone")
    private WebElement txt_phone;

    @FindBy(id = "billing_email")
    private WebElement txt_email;

    @FindBy(css = "input[id='billing_address_1']")
    private WebElement txt_StreetName_HouseNumber;

    @FindBy(id = "billing_address_2")
    private WebElement txt_Apartment;

    @FindBy(id = "select2-billing_country-container")
    private WebElement txt_Country;

    @FindBy(id = "order_comments")
    private WebElement txt_OrderNote;

    @FindBy(id = "place_order")
    private WebElement btn_placeOrder;

    @FindBy(id = "billing_city")
    private WebElement txt_town_city; // Note: duplicate identifier with txt_city, assuming this is intentional

    @FindBy(css = "ul[class='woocommerce-error']")
    private WebElement message_error;

    @FindBy(className = "select2-search__field")
    private WebElement txt_country_search;

    @FindBy(css = "input[id='billing_state']")
    private WebElement txt_state;

    @FindBy(css = "td[class='product-name']")
    private WebElement txt_productName;

    @FindBy(css = "h1[class='elementor-heading-title elementor-size-default']")
    private WebElement title_Checkout;

    // Getters
    public WebElement getFirstName() {
        return txt_first_name;
    }

    public WebElement getLastName() {
        return txt_last_name;
    }

    public WebElement getCompanyName() {
        return txt_company_name;
    }

    public WebElement getPostcode() {
        return txt_postcode;
    }

    public WebElement getCity() {
        return txt_city;
    }

    public WebElement getPhone() {
        return txt_phone;
    }

    public WebElement getEmail() {
        return txt_email;
    }

    public WebElement getStreetNameHouseNumber() {
        return txt_StreetName_HouseNumber;
    }

    public WebElement getApartment() {
        return txt_Apartment;
    }

    public WebElement getCountry() {
        return txt_Country;
    }

    public WebElement getOrderNote() {
        return txt_OrderNote;
    }

    public WebElement getPlaceOrderButton() {
        return btn_placeOrder;
    }

    public WebElement getTownCity() { // Seems redundant with getCity(), consider consolidating or renaming if different functionality is intended
        return txt_town_city;
    }

    public WebElement getErrorMessage() {
        return message_error;
    }

    public WebElement getCountrySearch() {
        return txt_country_search;
    }

    public WebElement getState() {
        return txt_state;
    }

    public WebElement getProductName() {
        return txt_productName;
    }

    public WebElement getCheckoutTitle() {
        return title_Checkout;
    }


}

