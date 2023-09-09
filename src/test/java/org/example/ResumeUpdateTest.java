package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ResumeUpdateTest {
    private final static String MONSTER_URL = "https://www.monster.ca/";
    private WebDriver driver;
    private String username;
    private String password;


    @BeforeMethod
    @Parameters({"username", "password"})
    public void setUp(String username, String password){
        this.username = username;
        this.password = password;

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testResumeUpdate(){
        driver.get(MONSTER_URL);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
