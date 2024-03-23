package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {
    public static WebDriver driver;

    @BeforeEach
    public void loadPage() {
        driver = new ChromeDriver(); 

        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}