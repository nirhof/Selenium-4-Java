package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.CommonOps;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginsHandler extends CommonOps {

    // Declaring WebDriver and plugin ID variables
    protected final WebDriver driver;
    String exeID;

    // Constructor to initialize WebDriver
    public PluginsHandler(WebDriver driver) {
        this.driver = driver;
    }

    // Method to install a plugin using the provided path
    public void installPlugin(String path) throws Exception {
        // Checking if the driver is an instance of FirefoxDriver
        if (driver instanceof FirefoxDriver) {
            // Converting the string path to a Path object
            Path pluginPath = Paths.get(path);

            // Install Plugin
            exeID = ((FirefoxDriver) driver).installExtension(pluginPath);
            System.out.println("Installing plugin");
        } else {
            // Throwing an exception if the driver is not a FirefoxDriver
            throw new Exception("Can only support Firefox at the moment");
        }
    }

    // Method to uninstall a plugin
    public void uninstallPlugin() {
        // Uninstall Plugin using the stored plugin ID
        ((FirefoxDriver) driver).uninstallExtension(exeID);
        System.out.println("Uninstalling plugin");
    }
}
