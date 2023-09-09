package org.example.pageobjects;

import org.example.util.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the experience editing page on the application.
 * This page allows users to modify their job experiences.
 */
public class EditExperiencePage {
    private final WebDriver driver;

    private final DriverUtils driverUtils;

    @FindBy(id = "company.name")
    private WebElement companyNameField;

    @FindBy(id = "dialog-control-profile-experience-edit-form-submit")
    private WebElement saveChangesButton;

    /**
     * Constructs the EditExperiencePage instance and initializes the web elements.
     *
     * @param driver the WebDriver instance.
     */
    public EditExperiencePage(WebDriver driver) {
        this.driver = driver;
        this.driverUtils = new DriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the text present in the company name field.
     *
     * @return the company name text.
     */
    public String getCompanyNameText() {
        return driverUtils.getAttribute(companyNameField, "value");
    }

    /**
     * Sets text in the company name field.
     *
     * @param text the text to be set.
     */
    public void setCompanyNameText(String text) {
        driverUtils.sendKeysToElement(companyNameField, text);
    }

    /**
     * Clicks the save changes button using JavaScript.
     */
    public void clickSaveChangesButton() {
        driverUtils.clickElementUsingJS(saveChangesButton);
    }
}
