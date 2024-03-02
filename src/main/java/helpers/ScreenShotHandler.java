package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import java.io.File;
import java.io.IOException;
import static org.testng.Assert.fail;

public class ScreenShotHandler {
    // Declaring variables to hold file path and WebDriver instance
    private final WebDriver driver;
    private final String filePath;

    // Constructor to initialize filePath and driver
    public ScreenShotHandler(String filePath, WebDriver driver) {
        this.filePath = filePath;
        this.driver = driver;
    }

    // Method to capture screenshot of a specific WebElement
    public void createElementScreenShot(WebElement element, String fileName) {
        // Capturing screenshot of the element
        File source = element.getScreenshotAs(OutputType.FILE);
        try {
            // Checking if the file name ends with ".png"
            if (!fileName.toLowerCase().endsWith(".png")) {
                fileName += ".png";
            }
            // Copying the screenshot to the specified file path
            FileUtils.copyFile(source, new File(this.filePath + fileName));
            System.out.println("Screenshot of element captured");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to capture full page screenshot
    public void createFullPageScreenShot(String fileName) {
        try {
            // Checking if the file name ends with ".png"
            if (!fileName.toLowerCase().endsWith(".png")) {
                fileName += ".png";
            }
            // Checking if the driver is Firefox, as full page screenshot is supported only in Firefox
            if (driver instanceof FirefoxDriver) {
                // Capturing full page screenshot
                File source = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File(this.filePath + fileName));
                System.out.println("Full page screenshot captured: " + fileName);
            } else {
                System.out.println("Full page screenshot is only supported in Firefox");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture full page screenshot: " + fileName);
        }
    }

    // Method to verify if an image exists
    public void verifyImage(String fileName) {
        Screen screen = new Screen();
        try {
            // Verifying if the image exists
            screen.find(filePath + fileName);
            System.out.println("Image found");

        } catch (FindFailed findFailed) {
            System.out.println("Image not found");
            findFailed.printStackTrace();
            fail("Image not found");
        }
    }

    // Method to verify if an image does not exist
    public void verifyImageNotAppear(String fileName) {
        Screen screen = new Screen();
        try {
            // Verifying if the image does not exist
            screen.find(filePath + fileName);
            System.out.println("Image found");

        } catch (FindFailed findFailed) {
            System.out.println("Image not found");
            findFailed.printStackTrace();
            fail("Image not found");
        }
    }
}