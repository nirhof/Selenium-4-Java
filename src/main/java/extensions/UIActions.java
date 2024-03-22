package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOps;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class UIActions extends CommonOps {

    @Step("Click on Element")
    public static void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Step("Update Text Element")
    public static void updateText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    @Step("accepting Alert and getting alert text")
    public static String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert popup = driver.switchTo().alert();
        String alertText = popup.getText();
        System.out.println("Alert text is : " + alertText);
        popup.dismiss();

        return alertText;
    }

    @Step("accepting Alert")
    public static void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert popup = driver.switchTo().alert();
        String alertText = popup.getText();
        System.out.println("Alert text is : " + alertText);
        popup.accept();
    }

    @Step("dismiss Alert")
    public static void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert popup = driver.switchTo().alert();
        String alertText = popup.getText();
        System.out.println("Alert text is : " + alertText);
        popup.dismiss();
    }

    @Step("Update Text in Elements")
    public static void updateElementsText(List<WebElement> elements, String[] values) {
        if (elements.size() != values.length) {
            System.out.println("Number of elements and values should be the same.");
            return;
        }

        for (int i = 0; i < elements.size(); i++) {

            WebElement element = elements.get(i);
            String value = values[i];
            wait.until(ExpectedConditions.visibilityOf(element));
            UIActions.click(element);
            System.out.println("clicked element " + element);
            // Clear the existing value in the field
            element.clear();
            // Type the new value into the field
            element.sendKeys(value);
            System.out.println("updated value " + value);
        }
    }

    @Step("get value of Element")
    public static String getValue(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String value = element.getAttribute("value");
        return value;

    }

    @Step("get text of Element")
    public static String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }


    @Step("Insert key")
    public static void PressKey(WebElement element, Keys keyvalue) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keyvalue);
    }

    @Step("Update Text Element Human Speed")
    public static void updateTextHumanSpeed(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        for (char ch : text.toCharArray()) {
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
            element.sendKeys(ch + "");
        }
    }

    @Step("Select drop down Element")
    public static void selectDropDownByVisibleText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select dropDown = new Select(element);
        dropDown.selectByVisibleText(text);
    }

    @Step("Select drop down Element")
    public static void selectDropDownByValue(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select dropDown = new Select(element);
        dropDown.selectByValue(value);
    }

    @Step("Select drop down Element")
    public static void selectDropDownByIndex(WebElement element, int index) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Select dropDown = new Select(element);
        dropDown.deselectByIndex(index);
    }

    @Step("mouse Hover Element")
    public static void mouseHover(WebElement element1, WebElement element2) {
        actions.moveToElement(element1).moveToElement(element2).click().build().perform();

    }

    @Step("mouse Hover Element")
    public static void mouseHover(WebElement element1) {
        actions.moveToElement(element1).click().build().perform();


    }

}
