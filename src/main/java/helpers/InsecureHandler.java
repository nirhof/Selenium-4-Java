package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.security.Security;

public class InsecureHandler extends DevToolsHandler {

    // Constructor to initialize InsecureHandler with WebDriver
    public InsecureHandler(WebDriver driver) {
        super(driver);
    }

    // Method to initialize security settings to ignore certificate errors
    public void initSecurity() {
        // Enable the Security DevTools protocol
        tool.send(Security.enable());
        // Set to ignore certificate errors
        tool.send(Security.setIgnoreCertificateErrors(true));
    }
}
