package com.z9y.recipes.services;

import com.z9y.recipes.models.Recipe;
import com.z9y.recipes.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        // Get the recipe from the database
        Recipe oldRecipe = recipeRepository.findById(recipe.getId(), Recipe.class);

        // Check if the recipe exists
        if (oldRecipe == null) {
            throw new RuntimeException("Recipe not found");
        }

        // Update the fields
        oldRecipe.setName(recipe.getName());
        oldRecipe.setCategory(recipe.getCategory());
        oldRecipe.setTag(recipe.getTag());
        oldRecipe.setImage(recipe.getImage());
        oldRecipe.setLink(recipe.getLink());
        oldRecipe.setFeatured(recipe.isFeatured());
        oldRecipe.setDetails(recipe.getDetails());
        oldRecipe.setIngredients(recipe.getIngredients());
        oldRecipe.setPreparation(recipe.getPreparation());
        oldRecipe.setMacro(recipe.getMacro());

        // Save the updated recipe
        return recipeRepository.save(oldRecipe);
    }

    public void deleteRecipeById(String id) {
        recipeRepository.deleteById(id);
    }


//    public List<Recipe> searchByIngredient(List<Map<String, Object>> ingredients) {
//        return recipeRepository.findByIngredientsContaining(ingredients);
//    }

}
