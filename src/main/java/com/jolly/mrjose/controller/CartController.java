package com.jolly.mrjose.controller;

import com.jolly.mrjose.domain.ICartManager;
import com.jolly.mrjose.persistence.model.Cart;
import com.jolly.mrjose.persistence.model.Recipe;
import com.jolly.mrjose.persistence.repository.CartRepository;
import com.jolly.mrjose.persistence.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CartController {
    private final CartRepository cartRepository;
    private final RecipeRepository recipeRepository;
    private final ICartManager cartManager;

    public CartController(@Autowired CartRepository cartRepository,
                          @Autowired RecipeRepository recipeRepository,
                          @Autowired ICartManager cartManager) {
        this.cartRepository = cartRepository;
        this.recipeRepository = recipeRepository;
        this.cartManager = cartManager;
    }

    @GetMapping("/carts/{id}")
    public Cart getCart(@PathVariable Long id) {
        return this.findCart(id);
    }

    @PostMapping("/carts/{cartId}/add_recipe")
    public Cart addRecipe(@PathVariable Long cartId,
                          @RequestParam(value = "recipe_id") Long recipeId) {
        // TODO: Better handle not found exceptions to send better info to frontend
        Recipe recipe = this.findRecipe(recipeId);
        Cart cart = this.findCart(cartId);

        this.cartManager.addRecipeToCart(cart, recipe);
        this.cartRepository.save(cart);

        return cart;
    }

    @DeleteMapping("/carts/{cartId}/recipes/{recipeId}")
    public Cart deleteRecipe(@PathVariable Long cartId, @PathVariable Long recipeId) {
        Recipe recipe = this.findRecipe(recipeId);
        Cart cart = this.findCart(cartId);

        this.cartManager.removeRecipe(cart, recipe);
        this.cartRepository.save(cart);

        return cart;
    }

    private Cart findCart(Long cartId) {
        return this.cartRepository
                .findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                               String.format("Invalid cart id=%s", cartId)));
    }

    private Recipe findRecipe(Long recipeId) {
        return this.recipeRepository
                .findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                               String.format("Invalid recipe id=%s", recipeId)));
    }
}
