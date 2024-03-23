package com.example.features;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.CheckoutPage;
import com.example.pages.LoginPage;
import com.example.pages.ProductPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void placeOrderByCheckoutPage() {
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

        // Go to checkout page
        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckout();

        // Filling checkout info
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInfo("Adriano", "Fukuda", "4000-100");

        //Confirm place order
        checkoutPage.confirmOrder();

        Assertions.assertEquals("Thank you for your order!\n" + //
                        "Your order has been dispatched, and will arrive just as fast as the pony can get there!\n" + //
                        "Back Home", checkoutPage.getMessageCheckoutComplete());
    }
    
}
