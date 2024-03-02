package helpers;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.network.Network;
import java.util.Optional;

public class BlockHandler extends DevToolsHandler {

    // Constructor to initialize BlockHandler with WebDriver
    public BlockHandler(WebDriver driver) {
        super(driver);
    }

    // Method to initialize network features
    public void initNetwork() {
        // Enable network features
        tool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    }

    // Method to block a specific resource
    public void blockResource(String resourceName) {
        // Specify URLs to block
        tool.send(Network.setBlockedURLs(ImmutableList.of(resourceName)));
    }
}
