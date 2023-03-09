package com.z9y.recipes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Map;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    private String id;
    private String recipeId;
    private String name;
    private String category;
    private List<String> tag;
    private String image;
    private String link;
    private boolean isFeatured;
    private Map<String, Integer> details;
    private List<Map<String, Object>> ingredients;
    private List<String> preparation;
    private Map<String, Integer> macro;
    @DocumentReference
    private List<Review> reviewIds;
}