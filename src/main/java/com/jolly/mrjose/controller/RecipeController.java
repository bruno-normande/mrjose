package com.jolly.mrjose.controller;

import com.jolly.mrjose.persistence.model.Recipe;
import com.jolly.mrjose.persistence.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(@Autowired RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes")
    public Iterable<Recipe> getRecipe() {
        //TODO: pagination
        return this.recipeRepository.findAll();
    }
}
