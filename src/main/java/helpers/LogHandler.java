package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.log.Log;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class LogHandler extends DevToolsHandler {

    public LogHandler(WebDriver driver) {
        super(driver);
    }

    public void initLogs() {
        tool.send(Log.enable());
    }

    public void printLogs() {
        System.out.println("Logs are written here:");
        tool.addListener(Log.entryAdded(), entry -> System.out.println(entry.getText()));
    }

    public void verifyTextInLog(String expected) {
        System.out.println("Verify the text " + expected + " is written in log:");
        tool.addListener(Log.entryAdded(), entry -> {
            try {
                assertTrue(entry.getText().contains(expected));
                System.out.println("Yeah, text was found in log");
            }
            catch (AssertionError e) {
                System.out.println("Sorry, text was not found. See details: " + e.getMessage());
                fail("Sorry, text was not found. See details: " + e.getMessage());
            }
        });
    }

    public void clearListeners() {
        tool.clearListeners();
    }
}