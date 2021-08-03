package com.jolly.mrjose.domain;

import com.jolly.mrjose.persistence.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class CartManagerTest {

    private Cart cart;
    private CartManager cartManager;

    @BeforeEach
    void setUp() {
        this.cart = new Cart();
        this.cartManager = new CartManager();
    }

    @Test
    void simpleAddProductToCartTest() {
        Product product = new Product();
        int quantity = 1;

        this.cartManager.addProductToCart(this.cart, product, quantity);

        CartItem cartItem = this.cart.getCartItems().stream().findFirst().get();
        Assertions.assertEquals(product, cartItem.getProduct());
        Assertions.assertEquals(quantity, cartItem.getQuantityInGrams());
    }

    @Test
    void multipleAddProductToCartTest() {
        Product product = new Product();
        int quantity1 = 1;
        int quantity2 = 10;
        int quantity3 = 5;

        this.cartManager.addProductToCart(this.cart, product, quantity1);
        this.cartManager.addProductToCart(this.cart, product, quantity2);
        this.cartManager.addProductToCart(this.cart, product, quantity3);

        CartItem cartItem = this.cart.getCartItems().stream().findFirst().get();
        Assertions.assertEquals(product, cartItem.getProduct());
        Assertions.assertEquals(quantity1 + quantity2 + quantity3, cartItem.getQuantityInGrams());
    }

    @Test
    void addRecipeToCartTest() {
        Product product1 = new Product();
        Product product2 = new Product();
        int quantity1 = 10;
        int quantity2 = 22;
        Recipe aRecipe = new Recipe();
        aRecipe.addIngredient(
                Ingredient.builder()
                        .product(product1)
                        .quantityInGrams(quantity1)
                        .build());
        aRecipe.addIngredient(
                Ingredient.builder()
                        .product(product2)
                        .quantityInGrams(quantity2)
                        .build()
        );

        this.cartManager.addRecipeToCart(this.cart, aRecipe);
        Set<CartItem> cartItems = this.cart.getCartItems();

        Assertions.assertEquals(2, cartItems.size());

        CartItem item = cartItems.stream()
                .filter(i -> i.getProduct() == product1)
                .findFirst()
                .get();
        Assertions.assertEquals(quantity1, item.getQuantityInGrams());

        item = cartItems.stream()
                .filter(i -> i.getProduct() == product2)
                .findFirst()
                .get();
        Assertions.assertEquals(quantity2, item.getQuantityInGrams());
    }

    // TODO: couldn't finish all the tests in time, but would try 100% coverage
}
