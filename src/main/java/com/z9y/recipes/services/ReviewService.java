package com.z9y.recipes.services;

import com.z9y.recipes.models.Recipe;
import com.z9y.recipes.models.Review;
import com.z9y.recipes.repositories.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String rating, String recipeId) {
        Review review = reviewRepository.insert(new Review(rating));

        mongoTemplate.update(Recipe.class)
                .matching(Criteria.where("recipeId").is(recipeId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
