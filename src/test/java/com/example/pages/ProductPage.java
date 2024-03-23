package com.example.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    
    protected WebDriver driver; 
    
    public ProductPage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().equals("Swag Labs")) {
            throw new IllegalStateException("This is not Product Page of Swag Labs," +
                " current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeFromCartButton;

    @FindBy(css = ".inventory_details_desc")
    WebElement productDetails;

    @FindBy(id = "back-to-products")
    WebElement backToProductsButton;

    @FindBy(css = ".btn_inventory")
    WebElement addToCartButtonProductDetails;

    @FindBy(css = ".shopping_cart_link")
    WebElement shoppingCartLink;

    public void addProductToCart() {
        addToCartButton.click();
    }

    public void removeProductFromCart() {
        removeFromCartButton.click();
    }

    public boolean validateProductRemoved() {
        return addToCartButton.isDisplayed();
    }

    public String getTextProductDetails() {
       return productDetails.getText();
    }

    public void backToProducts() {
        backToProductsButton.click();
    }

    public void goToShoppingCart() {
        shoppingCartLink.click();
    }

    public List<WebElement> getProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
        return products;
    }


    public List<WebElement> getImagesOfTheProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item_img .inventory_item_img"));
        return products;
    }

    public List<WebElement> productDetails() {
        List<WebElement> productDetails = driver.findElements(By.cssSelector(".inventory_item .inventory_item_description .inventory_item_name"));
        return productDetails;
    }

    public List<WebElement> getTextProductDescriptions() {
        List<WebElement> productDescriptions = driver.findElements(By.cssSelector(".inventory_item .inventory_item_description .inventory_item_desc"));
        return productDescriptions;
    }

    public List<WebElement> getProductPrices() {
        List<WebElement> productPrices = driver.findElements(By.cssSelector(".inventory_item .inventory_item_description .pricebar"));
        return productPrices;
    }

    public void addAllProductsToCart() {
        for(int i = 0; i < productDetails().size(); i++){
            List<WebElement> product = driver.findElements(By.cssSelector(".inventory_item .inventory_item_description .inventory_item_name"));
            product.get(i).click();
            addToCartButtonProductDetails.click();
            backToProductsButton.click();
        }
    }

    public void addSelectedProductsToCart(List<String> selectProducts) {
        for(String selectProduct : selectProducts){
            for(int i = 0; i < getProducts().size(); i++) {
                List<WebElement> product = driver.findElements(By.cssSelector(".inventory_list .inventory_item"));
                if(product.get(i).findElement(By.cssSelector(".inventory_item_name")).getText().equals(selectProduct)){
                    product.get(i).findElement(By.cssSelector(".inventory_item_name")).click();
                    addToCartButtonProductDetails.click();
                    backToProductsButton.click();
                }
            }
        }
    }

}
