package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import utilities.CommonOps;
import java.util.HashMap;
import java.util.List;


import static org.testng.Assert.*;

public class Verifications extends CommonOps {

    @Step("Verify element is displayed")
    public static void verifyElementIsDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        assertTrue(element.isDisplayed());
        System.out.println("element " + element + " is displayed");
    }

    @Step("Verify text in element")
    public static void verifyTextInElement(WebElement element, String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(element));

        System.out.println("actual text is " + element.getText());
        System.out.println("expected text is " + expectedText);
        assertEquals(element.getText(), expectedText);
    }

    @Step("Verify details of the row")
    public static void verifyRowDetails(List<WebElement> rowElements, List<String> expectedValues) {
        if (rowElements.size() != expectedValues.size()) {
            throw new IllegalArgumentException("Number of elements and expected values should match");
        }

        // Verify each element with its corresponding expected value
        for (int i = 0; i < rowElements.size(); i++) {
            WebElement element = rowElements.get(i);
            String actualValue = "";

            // Ensure the element is visible
            wait.until(ExpectedConditions.visibilityOf(element));

            // Try to get text, if empty, try to get value attribute
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                actualValue = text;
            } else {
                String valueAttribute = element.getAttribute("value");
                if (valueAttribute != null && !valueAttribute.isEmpty()) {
                    actualValue = valueAttribute;
                }
            }

            String expectedValue = expectedValues.get(i);
            System.out.println("Actual value: " + actualValue);
            System.out.println("Expected value: " + expectedValue);
            assertEquals(actualValue, expectedValue);
        }
    }
    @Step("Verify text in element")
    public static void verifyTextInElementWithAtrribute(WebElement element, String expectedText) {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.textToBePresentInElement(element, expectedText)));

        System.out.println("actual text is " + element.getText());
        System.out.println("expected text is " + expectedText);
        assertEquals(element.getText(), expectedText);
    }

    @Step("Verify element is disabled")
    public static void verifyElementIsDisabled(WebElement element) {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.attributeToBe(element, "disabled", "true")));
        assertEquals(element.getAttribute("disabled"), "true");
    }

    @Step("Verify text contained in element")
    public static void verifyTextPresentAndContainedInElement(WebElement element, String expectedText) {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.textToBePresentInElement(element, expectedText)));

        System.out.println("actual text is " + element.getText());
        System.out.println("expected text is " + expectedText);
        assertTrue(element.getText().contains(expectedText));
    }

    @Step("Verify text contained in element")
    public static void verifyTextContainedInElement(WebElement element, String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(element));

        System.out.println("actual text is " + element.getText());
        System.out.println("expected text is " + expectedText);
        assertTrue(element.getText().contains(expectedText));
    }

    @Step("Verify list of Strings are contained in element")
    public static void verifyAllTextsContainedInElement(WebElement element, List<String> expectedTexts) {
        wait.until(ExpectedConditions.visibilityOf(element));

        String actualText = element.getText();
        System.out.println("Actual text is: " + actualText);
        System.out.println("Expected texts are: " + expectedTexts);

        for (String expectedText : expectedTexts) {
            wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            assertTrue(actualText.contains(expectedText),
                    "Expected text '" + expectedText + "' not found in the actual text: '" + actualText + "'");
        }
    }

    @Step("Verify elements are empty")
    public static void verifyElementsAreEmpty(List<WebElement> elements) {
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
            String elementText = "";

            // Check if the element has a "value" attribute
            if (element.getAttribute("value") != null) {
                elementText = element.getAttribute("value");
            }
            // If "value" attribute is null, try to get text using getText()
            else {
                elementText = element.getText();
            }

            System.out.println("Element text is: " + elementText);
            softAssert.assertEquals(elementText, "", "Element is not empty.");
        }

        softAssert.assertAll("Some elements are not empty");
    }

    @Step("Verify number Of elements")
    public static void numberOfElements(List<WebElement> elements, int expected) {
        wait.until(ExpectedConditions.visibilityOf(elements.get(elements.size() - 1)));
        assertEquals(elements.size(), expected);
    }


    @Step("Verify title")
    public static void verifyTitle(String title) {
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().equals(title));
    }

    @Step("Verify title is not")
    public static void verifyTitleIsNot(String title) {
        System.out.println(driver.getTitle());
        assertFalse(driver.getTitle().equals(title));
    }


    @Step("Verify visibility  Of elements (soft Assertion")
    public static void visibilityOfElements(List<WebElement> elements) {
        for (WebElement element : elements) {
            softAssert.assertTrue(element.isDisplayed(), element.getText() + "is not displayed");
        }
        softAssert.assertAll("Some elements were not displayed");
    }

    @Step("Verify element Visually")
    public static void visualElement(String expectedImageName) {
        System.out.println("searching for image : " + expectedImageName);
        try {
            screen.find(getData("ImageRepo") + expectedImageName + ".png");
        } catch (FindFailed findFailed) {
            System.out.println("Error Comparing Image File: " + findFailed);
            fail("Error Comparing Image File: " + findFailed);
        }
    }

    @Step("Verify there are no elements")
    public static void verifyNoElements(List<WebElement> elements) {
        assertTrue((elements.size() == 0));
    }



    @Step("Verify text equals to text")
    public static void verifyText(String actual, String expetcted) {
        assertEquals(actual, expetcted);
    }

    @Step("Verify text in logs")
    public static void verifyTextContainedInLog(String expected) {
        System.out.println("Verify the text " + expected + " is written in log:");
        tool.addListener(Log.entryAdded(), entry -> {
            try {
                System.out.println(entry.getText());
                assertTrue(entry.getText().contains(expected));

            } catch (AssertionError e) {
                System.out.println("Sorry, text was not found. See details: " + e.getMessage());
                fail("Sorry, text was not found. See details: " + e.getMessage());
            }
        });
    }

    @Step("Verify number equals to number")
    public static void verifyNumber(int actual, int expetcted) {
        assertEquals(actual, expetcted);
    }


    @Step("Verify text to text")
    public static void verifyStringToString(String actualText, String expectedText) {
        System.out.println("actual text is " + actualText);
        System.out.println("expected text is " + expectedText);
        assertEquals(actualText, expectedText);
    }

    @Step("Verify list of element equals to list of string text")
    public static void VerifyTextInElements(List<WebElement> elements, List<String> expectedStrings) {

        // Check if the sizes of the lists are different
        if (elements.size() != expectedStrings.size()) {
            softAssert.fail("Lists have different sizes");
        }

        // Iterate through the elements and compare their text
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            String actualText = element.getText();

            if (actualText.isEmpty()) {
                actualText = element.getAttribute("value");
            }

            String expectedText = expectedStrings.get(i);
            System.out.println("expected text is : " + expectedText);
            System.out.println("element text is : " + actualText);

            // Compare the actual text with the expected text
            softAssert.assertEquals(actualText, expectedText, "Text mismatch at index " + i);
            System.out.println("element text is : " + actualText);
        }

        // Perform assertAll and return the result
        softAssert.assertAll();
    }

    @Step("Verify element orientation (soft Assertion")
    public static void verifyElementOrientaion(WebElement element, int Width, int height, int xLocation, int yLocation ) {
        System.out.println("actual Width of element : " + element.getRect().getWidth());
        System.out.println(" actual height of element : " + element.getRect().getHeight());
        System.out.println("actual X location of element : " + element.getRect().getX());
        System.out.println("actual Y location of element : " + element.getRect().getY());
        softAssert.assertEquals(element.getRect().getWidth(), Width);
        softAssert.assertEquals(element.getRect().getHeight(), height);
        softAssert.assertEquals(element.getRect().getX(), xLocation);
        softAssert.assertEquals(element.getRect().getY(), yLocation);
        softAssert.assertAll();
    }
}





