package com.jolly.mrjose.persistence.repository;

import com.jolly.mrjose.persistence.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
