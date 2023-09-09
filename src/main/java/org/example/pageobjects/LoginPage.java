package org.example.pageobjects;

import org.example.util.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final DriverUtils driverUtils;
    private final WebDriver driver;

    @FindBy(id = "email")
    private WebElement emailAddressField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[data-testid=auth0-continue-with-email-button]")
    private WebElement logInButton;

    /**
     * Constructor to initialize the elements and the utility driver for interaction.
     *
     * @param driver WebDriver instance for the page.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driverUtils = new DriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the provided email into the email address field.
     *
     * @param email The email to be entered.
     */
    public void enterEmail(String email) {
        driverUtils.sendKeysToElement(emailAddressField, email);
    }

    /**
     * Enters the provided password into the password field.
     *
     * @param password The password to be entered.
     */
    public void enterPassword(String password) {
        driverUtils.sendKeysToElement(passwordField, password);
    }

    /**
     * Clicks the login button to submit the login information.
     */
    public void submitLoginInformation() {
        driverUtils.clickElement(logInButton);
    }
}
