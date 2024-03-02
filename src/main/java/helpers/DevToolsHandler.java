package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.CommonOps;

// Abstract class to handle DevTools related operations
public abstract class DevToolsHandler extends CommonOps {

    // WebDriver instance
    protected final WebDriver driver;

    // Constructor to initialize DevToolsHandler with WebDriver
    public DevToolsHandler(WebDriver driver) {
        this.driver = driver;
        initializeDevTools();
    }

    // Method to initialize DevTools based on the WebDriver type
    private void initializeDevTools() {
        if (driver instanceof EdgeDriver) {
            // If WebDriver is EdgeDriver, get DevTools from it and create session
            tool = ((EdgeDriver) driver).getDevTools();
            tool.createSession();
        } else if (driver instanceof ChromeDriver) {
            // If WebDriver is ChromeDriver, get DevTools from it and create session
            tool = ((ChromeDriver) driver).getDevTools();
            tool.createSession();
        } else if (driver instanceof FirefoxDriver) {
            // If WebDriver is FirefoxDriver, get DevTools from it and create session
            tool = ((FirefoxDriver) driver).getDevTools();
            tool.createSession();
        } else {
            // Throw exception if WebDriver type is not supported
            throw new IllegalArgumentException("Unsupported WebDriver type");
        }
    }
}
