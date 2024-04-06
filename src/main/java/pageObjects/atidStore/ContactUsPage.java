package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactUsPage {

    @FindBy(id = "wpforms-15-field_0")
    public  WebElement txt_Name;

    @FindBy(id = "wpforms-15-field_5")
    public  WebElement txt_Subject;

    @FindBy(id = "wpforms-15-field_4")
    public  WebElement txt_Email;

    @FindBy(id = "wpforms-15-field_2")
    public  WebElement txt_Message;

    @FindBy(name = "wpforms[submit]")
    public  WebElement btn_sendMessage;

    @FindBy(id = "wpforms-confirmation-15")
    public  WebElement txt_confirmationSent;

    @FindBy(css = "span[class='elementor-icon-list-text']")
    public  List <WebElement> txt_AtidContactDetails;










}
