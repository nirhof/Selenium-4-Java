package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactUsPage {

    @FindBy(id = "wpforms-15-field_0")
    private  WebElement txt_Name;

    @FindBy(id = "wpforms-15-field_5")
    private  WebElement txt_Subject;

    @FindBy(id = "wpforms-15-field_4")
    private  WebElement txt_Email;

    @FindBy(id = "wpforms-15-field_2")
    private  WebElement txt_Message;

    @FindBy(name = "wpforms[submit]")
    private  WebElement btn_sendMessage;

    @FindBy(id = "wpforms-confirmation-15")
    private  WebElement txt_confirmationSent;

    @FindBy(css = "span[class='elementor-icon-list-text']")
    private  List <WebElement> txt_AtidContactDetails;

    // Getters
    public WebElement getNameTextField() {
        return txt_Name;
    }

    public WebElement getSubjectTextField() {
        return txt_Subject;
    }

    public WebElement getEmailTextField() {
        return txt_Email;
    }

    public WebElement getMessageTextField() {
        return txt_Message;
    }

    public WebElement getSendMessageButton() {
        return btn_sendMessage;
    }

    public WebElement getConfirmationSentText() {
        return txt_confirmationSent;
    }

    public List<WebElement> getAtidContactDetailsText() {
        return txt_AtidContactDetails;
    }

}
