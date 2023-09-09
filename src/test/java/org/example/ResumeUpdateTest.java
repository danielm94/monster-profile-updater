package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobjects.EditExperiencePage;
import org.example.pageobjects.LoginPage;
import org.example.pageobjects.MainPage;
import org.example.pageobjects.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ResumeUpdateTest {
    private final static String MAIN_PAGE_URL = "https://www.monster.ca/";
    private final static String PROFILE_PAGE_URL = "https://www.monster.ca/profile/detail";
    private WebDriver driver;
    private String username;
    private String password;


    @BeforeMethod
    @Parameters({"username", "password"})
    public void setUp(String username, String password) {
        this.username = username;
        this.password = password;

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testResumeUpdate() {
        driver.get(MAIN_PAGE_URL);
        var mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        var loginPage = new LoginPage(driver);
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.submitLoginInformation();

        mainPage.closeWelcomeDialogIfPresent();

        driver.get(PROFILE_PAGE_URL);

        var profilePage = new ProfilePage(driver);
        profilePage.editNthJobEntry(0);

        var editExperiencePage = new EditExperiencePage(driver);
        var companyNameText = editExperiencePage.getCompanyNameText();
        companyNameText = addOrRemovePeriodFromCompanyName(companyNameText);
        editExperiencePage.setCompanyNameText(companyNameText);
        editExperiencePage.clickSaveChangesButton();

        mainPage.clickLogoutButton();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    /**
     * Adds a period to the end of the company name if it has no period at the end of it, or removes the period from
     * the company name if it has it. The purpose of this is to always make a change to our resume,
     * thereby making our profile appear more often to recruiters.
     *
     * @param companyNameText The company name text to modify
     * @return The company name text with a period added or removed.
     */
    private String addOrRemovePeriodFromCompanyName(String companyNameText) {
        return companyNameText.charAt(companyNameText.length() - 1) == '.' ?
                companyNameText.substring(0, companyNameText.length() - 1) :
                companyNameText + ".";

    }
}
