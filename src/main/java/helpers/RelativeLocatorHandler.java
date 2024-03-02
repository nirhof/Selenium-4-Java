package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocatorHandler {

    private final WebDriver driver;

    // Constructor to initialize the WebDriver
    public RelativeLocatorHandler(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to find an element relative to another element based on given parameters.
     *
     * @param tagName      The tag name of the relative element.
     * @param relative     The relative position of the element (above, below, rightOf, leftOf, near).
     *                     <p>
     *                     for finding the base element:
     * @param locatorType  The type of locator to use for locating the relative element (id, name, tagName, className, linkText, partialLinkText, cssSelector, xpath).
     * @param locatorValue The value of the locator to use for locating the relative element.
     * @return The WebElement found relative to the specified parameters.
     * @throws Exception Throws an exception if an error occurs during the element lookup.
     */
        public WebElement getElement(String tagName, String relative, String locatorType, String locatorValue) throws Exception {
        RelativeLocator.RelativeBy relativeElement = null;

        // Switch case to determine the type of relative locator
        switch (relative) {
            case "above": {
                relativeElement = RelativeLocator.with(By.tagName(tagName)).above(byLocator(locatorType, locatorValue));
                System.out.println("find relative element : above");
                break;
            }
            case "below": {
                relativeElement = RelativeLocator.with(By.tagName(tagName)).below(byLocator(locatorType, locatorValue));
                System.out.println("find relative element : below");
                break;
            }
            case "rightOf": {
                relativeElement = RelativeLocator.with(By.tagName(tagName)).toRightOf(byLocator(locatorType, locatorValue));
                System.out.println("find relative element : rightOf");
                break;
            }
            case "leftOf": {
                relativeElement = RelativeLocator.with(By.tagName(tagName)).toLeftOf(byLocator(locatorType, locatorValue));
                System.out.println("find relative element : leftOf");
                break;
            }
            case "near": {
                relativeElement = RelativeLocator.with(By.tagName(tagName)).near(byLocator(locatorType, locatorValue));
                System.out.println("find relative element : near");
                break;
            }
            default:
                throw new Exception("error passing relative locator");
        }
        // Return the found WebElement
        return driver.findElement(relativeElement);
    }

    // Method to create a By locator based on type and value
    public By byLocator(String locatorType, String locatorValue) throws Exception {
        switch (locatorType) {
            // Different cases to handle different locator types
            case "id": {
                System.out.println("find by id");
                return By.id(locatorValue);
            }
            case "name": {
                System.out.println("find by name");
                return By.name(locatorValue);
            }
            case "tagName": {
                System.out.println("find by tagName");
                return By.tagName(locatorValue);
            }
            case "className": {
                System.out.println("find by className");
                return By.className(locatorValue);
            }
            case "linkText": {
                System.out.println("find by linkText");
                return By.linkText(locatorValue);
            }
            case "partialLinkText": {
                System.out.println("find by partialLinkText");
                return By.partialLinkText(locatorValue);
            }
            case "cssSelector": {
                System.out.println("find by cssSelector");
                return By.cssSelector(locatorValue);
            }
            case "xpath": {
                System.out.println("find by xpath");
                return By.xpath(locatorValue);
            }
            default:
                throw new Exception("no locator found");
        }
    }


    // Method to find an element relative to a base element with a given tag name and position
    public WebElement findElementRelativeToBaseElementWithTagName(WebElement baseElement, String relativeElementTagName, String relativeElementPosition) {
        RelativeLocator.RelativeBy relativeBy = null;

        // Switch case to determine the position of the relative element
        switch (relativeElementPosition.toLowerCase()) {
            case "below":
                relativeBy = RelativeLocator.with(By.tagName(relativeElementTagName)).below(baseElement);
                break;
            case "above":
                relativeBy = RelativeLocator.with(By.tagName(relativeElementTagName)).above(baseElement);
                break;
            case "rightof":
                relativeBy = RelativeLocator.with(By.tagName(relativeElementTagName)).toRightOf(baseElement);
                break;
            case "leftof":
                relativeBy = RelativeLocator.with(By.tagName(relativeElementTagName)).toLeftOf(baseElement);
                break;
            case "near":
                relativeBy = RelativeLocator.with(By.tagName(relativeElementTagName)).near(baseElement);
                break;
            default:
                throw new IllegalArgumentException("Invalid relative position: " + relativeElementPosition);
        }

        // Return the found WebElement
        return driver.findElement(relativeBy);
    }
}

