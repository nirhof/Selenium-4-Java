package sanity;


import extensions.Verifications;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.Webflows;
import java.util.Arrays;
import java.util.List;

public class ShopDemoContactUs extends CommonOps {

    @Test(description = "Test01 - open new tab of contact page and verify title")
    @Description("This test open new tab of contact page and verify the page title")
    public void test01_verifyContactUsPageTitle() {
        // Verify the title of the contact us page
        Verifications.verifyTitle("Contact Us – ATID Demo Store");
    }

    @Test(description = "Test02 - Verify Contact Us Sending Message")
    @Description("This test verifies the functionality of sending a message through the Contact Us form.")
    public void test02_verifyContactUsSendingMessage() {
        // Send message
        Webflows.sendMessage("kuku", "Hello", "kuku@gmail.com", "Atid college are the best");

        // Verify confirmation message
        Verifications.verifyTextInElement(contactUsPage.getConfirmationSentText(), "Thanks for contacting us! We will be in touch with you shortly.");
    }

    @Test(description = "Test03 - Verify Atid Store Contact Details")
    @Description("This test verifies the accuracy of the contact details provided by Atid Store.")
    public void test03_verifyAtidStoreContactDetails() {
        // Retrieve actual store email and phone elements
        WebElement actualStoreEmail = contactUsPage.getAtidContactDetailsText().get(0);
        WebElement actualStorePhone = contactUsPage.getAtidContactDetailsText().get(1);

        // Create a list of actual contact details
        List<WebElement> actualContactDetails = Arrays.asList(
                actualStoreEmail,
                actualStorePhone
        );

        // Define expected store email and phone
        String expectedStoreEmail = "hello@atid.store";
        String expectedStorePhone = "972-52-1234567";

        // Create a list of expected values
        List<String> expectedValues = Arrays.asList(
                expectedStoreEmail,
                expectedStorePhone
        );

        // Verify the accuracy of contact details
        Verifications.verifyRowDetails(actualContactDetails, expectedValues);
    }

    @BeforeMethod
    public void beforeMethod() {
        // Open new tab and switch to it
        tabsWindowsHandler.openAndSwitchNewTab("https://atid.store/contact-us/");

    }

    @AfterMethod
    public void afterMethod() {
        // Close the tab window with the specified title
        tabsWindowsHandler.closedTabWindow("ATID Demo Store – ATID College Demo Store for Practicing QA Automation");

        // Switch back to the tab window with index 0
        tabsWindowsHandler.switchTabWindow(0);

        // Navigate back to the Atid Store URL after each test method execution
        driver.get(getData("url"));
    }
}
