package org.example.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class providing common WebDriver operations.
 * <p>
 * This class abstracts commonly used WebDriver actions such as clicking on an element, retrieving text, and sending keys.
 * It uses explicit waits to handle synchronization challenges.
 * </p>
 */
public class DriverUtils {

    private final WebDriver driver;
    private final Duration timeout;

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

    /**
     * Constructor with a specified timeout.
     *
     * @param driver  WebDriver instance.
     * @param timeout Duration for explicit waits.
     */
    public DriverUtils(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    /**
     * Constructor with default timeout (10 seconds).
     *
     * @param driver WebDriver instance.
     */
    public DriverUtils(WebDriver driver) {
        this(driver, DEFAULT_TIMEOUT);
    }


    /**
     * Finds an element and returns it once it is visible.
     *
     * @param locator The locator of the element.
     * @return The found element.
     */
    public WebElement findElement(By locator) {
        return waitForVisibility(locator);
    }

    /**
     * Clicks on an element identified by its By locator after waiting for its visibility.
     *
     * @param by By locator of the element.
     */
    public void clickElement(By by) {
        waitForElementToBeClickable(by).click();
    }

    /**
     * Clicks on a WebElement after waiting for its visibility.
     *
     * @param element WebElement to be clicked.
     */
    public void clickElement(WebElement element) {
        waitForVisibility(element).click();
    }

    /**
     * Retrieves text from an element identified by its By locator after waiting for its visibility.
     *
     * @param by By locator of the element.
     * @return Text from the WebElement.
     */
    public String getElementText(By by) {
        return waitForVisibility(by).getText();
    }

    /**
     * Retrieves text from a WebElement after waiting for its visibility.
     *
     * @param element WebElement from which text is retrieved.
     * @return Text from the WebElement.
     */
    public String getElementText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    public String getAttribute(By by, String attribute) {
        return waitForVisibility(by).getAttribute(attribute);
    }

    public String getAttribute(WebElement element, String attribute) {
        return waitForVisibility(element).getAttribute(attribute);
    }

    /**
     * Sends a sequence of keys to an element identified by its By locator after waiting for its visibility.
     *
     * @param by   By locator of the element.
     * @param text Text to be sent.
     */
    public void sendKeysToElement(By by, String text) {
        var element = waitForVisibility(by);
        element.sendKeys(text);
    }

    /**
     * Sends a sequence of keys to a WebElement after waiting for its visibility.
     *
     * @param element WebElement to which keys are sent.
     * @param text    Text to be sent.
     */
    public void sendKeysToElement(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    /**
     * Checks if an element identified by its By locator is present in the DOM.
     *
     * @param by By locator of the element.
     * @return true if the element is present, false otherwise.
     */
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            driver.findElement(by);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Checks if a WebElement is present and visible in the DOM.
     *
     * @param element WebElement to be checked.
     * @return true if the WebElement is visible, false otherwise.
     */
    public boolean isElementPresent(WebElement element) {
        try {
            waitForVisibility(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clicks on the given element using JavaScript.
     *
     * @param element the WebElement to be clicked.
     */
    public void clickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Clicks on the element located by the given selector using JavaScript.
     *
     * @param by the By object representing the locator strategy.
     */
    public void clickElementUsingJS(By by) {
        WebElement element = driver.findElement(by);
        clickElementUsingJS(element);
    }

    /**
     * Clears the content of an input element identified by its By locator after waiting for its visibility.
     *
     * @param by By locator of the input element.
     */
    public void clearInputField(By by) {
        var element = waitForVisibility(by);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    /**
     * Clears the content of an input WebElement after waiting for its visibility.
     *
     * @param element WebElement that represents the input.
     */
    public void clearInputField(WebElement element) {
        waitForVisibility(element);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    private WebElement waitForElementToBeClickable(By by) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    private WebElement waitForVisibility(By by) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private WebElement waitForVisibility(WebElement element) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
    }
}
