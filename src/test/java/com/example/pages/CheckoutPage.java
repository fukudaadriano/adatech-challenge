package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    
    protected WebDriver driver; 
    
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().equals("Swag Labs")) {
            throw new IllegalStateException("This is not Checkout Page of Swag Labs," +
                " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(css = ".checkout_complete_container")
    WebElement checkoutComplete;

    public void fillCheckoutInfo(String name, String lastName, String postalCode) {
        firstNameInput.sendKeys(name);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        continueButton.click();
    }

    public void confirmOrder() {
        finishButton.click();
    }

    public String getMessageCheckoutComplete() {
        return checkoutComplete.getText();
    }
}
