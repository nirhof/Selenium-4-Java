package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import java.util.Optional;

public class GeoLocationHandler extends DevToolsHandler {

    // Constructor to initialize TimeZoneHandler with WebDriver
    public GeoLocationHandler(WebDriver driver) {
        super(driver);
    }

    public void setGeoLocation(String latitude, String longitude) {
        tool.send(Emulation.setGeolocationOverride(Optional.of(Double.parseDouble(latitude)), Optional.of(Double.parseDouble(longitude)), Optional.of(100)));
    }
}
