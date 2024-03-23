package com.example.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    protected WebDriver driver; 
    
    public CartPage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().equals("Swag Labs")) {
            throw new IllegalStateException("This is not Cart Page of Swag Labs," +
                " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(css = ".cart_list .cart_item")
    List<WebElement> cartList;

    @FindBy(css = ".shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(id = "checkout")
    WebElement checkoutButton;


    public List<WebElement> itemsCartList() {
        return cartList;
    }

    public void quantityCartBadge() {
        cartBadge.getText();
    }

    public void goToCheckout() {
        checkoutButton.click();
    }

}
