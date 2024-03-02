package pageObjects.atidStore;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AboutPage {

    @FindBy(className = "elementor-image-box-description")
    public List<WebElement> description;

}
