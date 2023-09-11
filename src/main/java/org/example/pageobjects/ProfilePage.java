package org.example.pageobjects;

import org.example.util.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the user's profile page, providing interaction with its elements.
 */
public class ProfilePage {
    private static final Logger LOG = LoggerFactory.getLogger(ProfilePage.class);
    private static final String FORMATABLE_EDIT_EXPERIENCE_ELEMENT_LOCATOR = "a[data-testid=profile-experience-%s-editlink]";
    private final DriverUtils driverUtils;
    private final WebDriver driver;

    /**
     * Constructor to initialize the utility driver for interaction.
     *
     * @param driver WebDriver instance for the page.
     */
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.driverUtils = new DriverUtils(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the edit icon for the Nth job entry on the profile.
     *
     * @param n the job entry number.
     */
    public void editNthJobEntry(int n){
        var editJobIcon = getEditExperienceElement(n);
        LOG.info("Clicking edit job entry button number {}", n);
        driverUtils.clickElementUsingJS(editJobIcon);
    }

    private WebElement getEditExperienceElement(int n) {
        var formattedLocator = String.format(FORMATABLE_EDIT_EXPERIENCE_ELEMENT_LOCATOR, n);
        LOG.info("Locating Edit Experience element using the following locator - {}", formattedLocator);
        var locator = By.cssSelector(formattedLocator);
        return driverUtils.findElement(locator);
    }
}
