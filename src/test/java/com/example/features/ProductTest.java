package com.example.features;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.ProductPage;

public class ProductTest extends BaseTest {
    
    @Test
    @DisplayName("Validate if the product list are differents")
    public void showProductList() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("problem_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);

        // Comparing all product image for the page
        // TO DO extract to method
        List<WebElement> products = productPage.getImagesOfTheProducts();
        for (WebElement product : products) {
            Assertions.assertNotEquals(products.get(0).getAttribute("src"), product.getAttribute("src"), "The images are equals");
        }
    }

    @Test
    @DisplayName("Add and remove product from cart in home page")
    public void addAndRemoveProductToCart(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("problem_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart();
        productPage.removeProductFromCart();
        Assertions.assertTrue(productPage.validateProductRemoved());
    }

    @Test
    @DisplayName("Validating all product descriptions between home page and product details page")
    public void showProductDetails() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsUser("problem_user", "secret_sauce");
        ProductPage productPage = new ProductPage(driver);

        // Comparing all product description from home page and product details page
        // TO DO extract to method
        List<WebElement> productDescriptions = productPage.productDetails();
        for (int i = 0; i < productDescriptions.size(); i++) {
            // Due back to product to do page refresh, It is necessary get element again, because error no state element
            List<WebElement> productDetails = driver.findElements(By.cssSelector(".inventory_item .inventory_item_description .inventory_item_name"));
            List<WebElement> productDetailDescriptions = productPage.getTextProductDescriptions();
            String productDetailFromHome = productDetailDescriptions.get(i).getText();
            productDetails.get(i).click();
            Assertions.assertNotEquals(productDetailFromHome, productPage.getTextProductDetails(), "The product details are differents");
            productPage.backToProducts();
        }
    }
}
