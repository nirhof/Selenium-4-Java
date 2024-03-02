package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import java.util.ArrayList;

public class TabsWindowsHandler {
    private WebDriver driver;

    // Constructor to initialize WebDriver
    public TabsWindowsHandler(WebDriver driver) {
        this.driver = driver;
    }

    // Method to open and switch to a new window with the given URL
    public void openAndSwitchNewWindow(String url) {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(url);
        System.out.println("Opening window: URL is " + url);
    }

    // Method to open and switch to a new tab with the given URL
    public void openAndSwitchNewTab(String url) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
        System.out.println("Opening tab: URL is " + url);
    }

    // Method to close a tab or window with the given title
    public void closedTabWindow(String title) {
        // Iterate through all window handles
        for (String winHandle : driver.getWindowHandles()) {
            // Switch to each window handle
            driver.switchTo().window(winHandle);
            // Check if the title of the current window matches the given title
            if (driver.getTitle().equalsIgnoreCase(title)) {
                // If matched, close the window
                break;
            }
        }
        driver.close();
    }

    // Method to close a tab or window by index
    public void closedTabWindow(int index) {
        // Get all window handles into an ArrayList
        ArrayList<String> tabWindow = new ArrayList<>(driver.getWindowHandles());
        // Switch to the window handle at the given index
        driver.switchTo().window(tabWindow.get(index));
        // Close the window
        driver.close();
    }
}
