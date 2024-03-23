package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.BaseTest;

public class LoginPage extends BaseTest {

    protected WebDriver driver; 
    
    public LoginPage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().equals("Swag Labs")) {
            throw new IllegalStateException("This is not Login Page of Swag Labs," +
                " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public void loginAsUser(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }
        
}
