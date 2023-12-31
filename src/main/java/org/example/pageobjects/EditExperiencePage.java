package org.example.pageobjects;

import org.example.util.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the experience editing page on the application.
 * This page allows users to modify their job experiences.
 */
public class EditExperiencePage {
    private static final Logger LOG = LoggerFactory.getLogger(EditExperiencePage.class);
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
        var companyNameText = driverUtils.getAttribute(companyNameField, "value");
        LOG.info("Company Name field had the following text - {}", companyNameText);
        return companyNameText;
    }

    /**
     * Sets text in the company name field.
     *
     * @param text the text to be set.
     */
    public void setCompanyNameText(String text) {
        LOG.info("Entering the following text into the Company Name field - {}", text);
        driverUtils.clearInputField(companyNameField);
        driverUtils.sendKeysToElement(companyNameField, text);
    }

    /**
     * Clicks the save changes button using JavaScript.
     */
    public void clickSaveChangesButton() {
        LOG.info("Clicking Save Changes button.");
        driverUtils.clickElementUsingJS(saveChangesButton);
    }
}
