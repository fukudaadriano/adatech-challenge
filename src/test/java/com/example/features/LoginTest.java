package com.example.features;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.example.BaseTest;
import com.example.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Login with user valid should be logged successfuly")
    public void loginValid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("standard_user", "secret_sauce");
        Assertions.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed(), "The home page is not displayed");
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl(), "The url is not the expected");
    }

    @Test
    @DisplayName("Login with user invalid should not be logged")
    public void loginInvalid() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("locked_out_user", "secret_sauce");
        Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.", driver.findElement(By.className("error-message-container")).getText(), "The message is not the expected");
        Assertions.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl(), "The url is not the expected");
    }
}
