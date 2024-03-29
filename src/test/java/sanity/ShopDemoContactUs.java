package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;

@Listeners(utilities.Listeners.class)
public class ShopDemoContactUs extends CommonOps {

    @Test(description = "Test01 - open new tab of contact page and verify title")
    @Description("This test open new tab of contact page and verify the page title")
    public void test01_verifyContactUsPageTitle() {

        //open new Tab and switch to it
        tabsWindowsHandler.openAndSwitchNewTab("https://atid.store/contact-us/");
        // Verify the title of the contact us page
        Verifications.verifyTitle("Contact Us – ATID Demo Store");
    }

    @AfterMethod
    public void afterMethod() {
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
