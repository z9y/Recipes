package com.z9y.recipes.repositories;

import com.z9y.recipes.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByNameRegexIgnoreCase(String searchTerm);
    Optional<Recipe> findRecipeByRecipeId(String recipeId);

    Recipe findById(String id, Class<Recipe> recipeClass);
}
