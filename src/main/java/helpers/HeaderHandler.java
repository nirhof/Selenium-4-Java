
package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import java.util.HashMap;
import java.util.Optional;

public class HeaderHandler extends DevToolsHandler {

    // Constructor to initialize HeaderHandler with WebDriver
    public HeaderHandler(WebDriver driver) {
        super(driver);
    }

    // Method to initialize network features
    public void initHeader() {
        // Enable network features
        tool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    }


    //   Method to add custom headers to network requests.
    //  @param headers A map containing header names and values.
    public void addHeader(HashMap<String, Object> header) {
        // Create Headers object from the provided map
        Headers data = new Headers(header);
        // Set extra HTTP headers for network requests
        tool.send(Network.setExtraHTTPHeaders(data));
    }
}
