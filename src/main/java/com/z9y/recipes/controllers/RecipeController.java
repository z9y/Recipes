package com.z9y.recipes.controllers;

import com.z9y.recipes.models.Recipe;
import com.z9y.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @GetMapping
    public ResponseEntity<List<Recipe>> allRecipes() {
        return new ResponseEntity<List<Recipe>>(recipeService.getRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<Optional<Recipe>> getRecipe(@PathVariable String recipeId) {
        return new ResponseEntity<Optional<Recipe>>(recipeService.getRecipe(recipeId), HttpStatus.OK);
    }

    // path : "/?search={search}"
    @GetMapping("/")
    public List<Recipe> getRecipes(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            // If a search term is provided, call the getRecipes() method of the RecipeService with the search term
            return recipeService.getRecipes(search);
        } else {
            // Otherwise, return all recipes
            return recipeService.getRecipes();
        }
    }

}
