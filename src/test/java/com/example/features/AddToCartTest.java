package com.example.features;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.LoginPage;
import com.example.pages.ProductPage;

public class AddToCartTest extends BaseTest {

    @Test
    @DisplayName("Add all products to cart and confirm that they have been added successfully via home page")
    public void selectAllProductAndAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("standard_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.addAllProductsToCart();

        // Checking if all products have been add to cart
        // TO DO extract to method
        List<WebElement> products = productPage.getProductPrices();
        for (WebElement product : products) {
            Assertions.assertEquals("Remove", product.findElement(By.cssSelector(".btn_inventory")).getText(), "The product was not add to cart");
        }
    }

    @Test
    @DisplayName("Select some products for add to cart and confirm they have been added successfully via home page")
    public void selectSomeProductsAndAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("standard_user", "secret_sauce");

        // Add selected product to cart
        ProductPage productPage = new ProductPage(driver);
        List<String> selectedProducts = new ArrayList<String>();
        selectedProducts.add("Sauce Labs Bike Light");
        selectedProducts.add("Sauce Labs Onesie");
        selectedProducts.add("Sauce Labs Bolt T-Shirt");
        productPage.addSelectedProductsToCart(selectedProducts);

        // Assertion that for products have been added successfully
        // TO DO extract to method
        List<WebElement> products = productPage.getProducts();
        for(String selectProduct : selectedProducts){
            for (WebElement product : products) {
                if(product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(selectProduct)){
                    Assertions.assertEquals("Remove", product.findElement(By.cssSelector(".btn_inventory")).getText(), "The product was not add to cart");
                }
            }
        }
    }

    @Test
    @DisplayName("Select some products for add to cart and confirm they have been added successfully via shopping cart page")
    public void verifySomeProductsAddToCartInPageCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("standard_user", "secret_sauce");

        // Add products to cart
        ProductPage productPage = new ProductPage(driver);
        List<String> selectedProducts = new ArrayList<String>();
        selectedProducts.add("Sauce Labs Bike Light");
        selectedProducts.add("Sauce Labs Onesie");
        selectedProducts.add("Sauce Labs Bolt T-Shirt");
        productPage.addSelectedProductsToCart(selectedProducts);

        // Go to shopping cart
        productPage.goToShoppingCart();
        CartPage cartPage = new CartPage(driver);

        Assertions.assertEquals(selectedProducts.size(), cartPage.itemsCartList().size(), "The quantity is not correct");

        // Add products in the shopping cart page to a list for compare with products have been added
        // TO DO extract to method
        List<String> productsShoppingCart = new ArrayList<String>();
        for(WebElement itemCartList : cartPage.itemsCartList()){
            productsShoppingCart.add(itemCartList.findElement(By.cssSelector(".inventory_item_name")).getText());
        }
    
        Assertions.assertEquals(selectedProducts, productsShoppingCart);
    }
    
}
