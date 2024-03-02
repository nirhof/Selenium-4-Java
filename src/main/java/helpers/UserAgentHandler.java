package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import java.util.Optional;

public class UserAgentHandler extends DevToolsHandler {

    // Constructor to initialize UserAgentHandler with WebDriver
    public UserAgentHandler(WebDriver driver) {
        super(driver);
    }

    // Method to set the user agent string
    public void setUserAgent(String value) {
        // Use the DevTools API to set the user agent override
        // Pass empty strings for platform and userAgentMetadata as they are optional
        tool.send(Emulation.setUserAgentOverride(value, Optional.empty(), Optional.empty(), Optional.empty()));
    }
}
