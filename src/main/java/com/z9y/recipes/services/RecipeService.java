package com.z9y.recipes.services;

import com.z9y.recipes.models.Recipe;
import com.z9y.recipes.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipe(String recipeId){
        return recipeRepository.findRecipeByRecipeId(recipeId);
    }

    public List<Recipe> getRecipes(String searchTerm) {
        String regex = "(?i).*" + searchTerm + ".*";
        return recipeRepository.findByNameRegexIgnoreCase(regex);
    }

}
