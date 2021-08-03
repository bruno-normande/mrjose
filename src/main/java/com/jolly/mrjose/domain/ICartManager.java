package com.jolly.mrjose.domain;

import com.jolly.mrjose.persistence.model.Cart;
import com.jolly.mrjose.persistence.model.Product;
import com.jolly.mrjose.persistence.model.Recipe;

public interface ICartManager {

    /**
     * Adds a Recipe to a Cart.
     * It adds all products from the recipe's ingredients to the cart.
     *
     * @param cart the cart to add the recipe to
     * @param recipe recipe to be added
     */
    void addRecipeToCart(Cart cart, Recipe recipe);

    /**
     * Adds a Product to a Cart.
     * It adds a products to the cart, using the quantity passed.
     *
     * @param cart the cart to add the recipe to
     * @param product product to be added
     * @param quantityInGrams quantity of product to add
     */
    void addProductToCart(Cart cart, Product product, int quantityInGrams);

    /**
     * Remove all of Recipe's products from the cart.
     *
     * @param cart the cart to remove the recipe from
     * @param recipe recipe to be removed
     */
    void removeRecipe(Cart cart, Recipe recipe);

    /**
     * Removes a Product from a Cart.
     * It removes a products from the cart, using the quantity passed.
     *
     * @param cart the cart to add the recipe to
     * @param product product to be added
     * @param quantityInGrams quantity of product to add
     */
    void removeProductFromCart(Cart cart, Product product, int quantityInGrams);
}
