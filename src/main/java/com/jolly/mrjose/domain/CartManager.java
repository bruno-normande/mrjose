package com.jolly.mrjose.domain;

import com.jolly.mrjose.persistence.model.Cart;
import com.jolly.mrjose.persistence.model.CartItem;
import com.jolly.mrjose.persistence.model.Product;
import com.jolly.mrjose.persistence.model.Recipe;
import org.springframework.stereotype.Service;

@Service
public class CartManager implements ICartManager {
    /*
     * We should implement a hashmap to avoid traversing cart's items
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRecipeToCart(Cart cart, Recipe recipe) {
        recipe.getIngredients().forEach(ingredient -> {
            this.addProductToCart(cart, ingredient.getProduct(), ingredient.getQuantityInGrams());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProductToCart(Cart cart, Product product, int quantityInGrams) {
        cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(item -> item.addQuantityInGrams(quantityInGrams),
                                 () -> this.createCartItemWithQuantity(cart, product, quantityInGrams));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRecipe(Cart cart, Recipe recipe) {
        recipe.getIngredients()
                .forEach(ingredient -> this.removeProductFromCart(cart,
                                                                  ingredient.getProduct(),
                                                                  ingredient.getQuantityInGrams()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProductFromCart(Cart cart, Product product, int quantityInGrams) {
        cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .ifPresent(cartItem -> {
                    int remainingGrams = Math.max(cartItem.getQuantityInGrams() - quantityInGrams, 0) ;
                    /* We choose not to remove the item, the interface can filter items with quantity 0,
                    *  instead we save the 0 quantity. We can use it later to see what products were put
                    *  in the cart and removed later. */
                    cartItem.setQuantityInGrams(remainingGrams);
                });
    }

    private void createCartItemWithQuantity(Cart cart, Product product, int quantityInGrams) {
        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantityInGrams(quantityInGrams)
                .build();
        cart.addItem(cartItem);
    }
}
