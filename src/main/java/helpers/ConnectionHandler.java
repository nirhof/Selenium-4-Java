package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import java.util.Optional;

public class ConnectionHandler extends DevToolsHandler {

    // Constructor to initialize ConnectionHandler with WebDriver
    public ConnectionHandler(WebDriver driver) {
        super(driver);
    }

    // Method to set the browser to offline mode
    public void setOfflineMode() {
        // Emulate network conditions with no latency, download, or upload throughput
        tool.send(Network.emulateNetworkConditions(true, 0, -1, -1, Optional.empty()));
    }

    // Method to emulate network conditions
    public void emulateNetwork(int latency, int downloadThroughput, int uploadThroughput, ConnectionType connectionType) {
        // Emulate network conditions with specified latency, download and upload throughput, and connection type
        tool.send(Network.emulateNetworkConditions(false, latency, downloadThroughput, uploadThroughput, Optional.of(connectionType)));
    }
}
