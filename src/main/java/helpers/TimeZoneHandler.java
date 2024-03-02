package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

public class TimeZoneHandler extends DevToolsHandler {

    // Constructor to initialize TimeZoneHandler with WebDriver
    public TimeZoneHandler(WebDriver driver) {
        super(driver);
    }

    public void setTimeZone(String location) {

        tool.send(Emulation.setTimezoneOverride(location));
    }
}
