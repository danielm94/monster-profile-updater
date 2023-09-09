package org.example.pageobjects;

import org.example.util.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the main page of the application, providing interaction with its elements.
 */
public class MainPage {
    private final DriverUtils driverUtils;
    private final WebDriver driver;

    @FindBy(xpath = "//a//span[contains(text(),'Log in')]")
    private WebElement loginButton;

    @FindBy(css = "div[role=dialog] button[data-testid=dismiss-iconbutton]")
    private WebElement closeWelcomeDialogButton;

    @FindBy(css = "button[aria-label='Log Out']")
    private WebElement logOutButton;

    /**
     * Constructor to initialize the elements and the utility driver for interaction.
     *
     * @param driver WebDriver instance for the page.
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.driverUtils = new DriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Performs a click action on the login button.
     */
    public void clickLoginButton() {
        driverUtils.clickElement(loginButton);
    }

    /**
     * Closes the welcome dialog if it is present on the page.
     */
    public void closeWelcomeDialogIfPresent() {
        if (isWelcomeDialogPresent()) {
            clickCloseWelcomeDialogButton();
        }
    }

    /**
     * Performs a click action on the logout button.
     */
    public void clickLogoutButton() {
        driverUtils.clickElement(logOutButton);
    }

    private boolean isWelcomeDialogPresent() {
        return driverUtils.isElementPresent(closeWelcomeDialogButton);
    }

    private void clickCloseWelcomeDialogButton() {
        driverUtils.clickElement(closeWelcomeDialogButton);
    }
}
