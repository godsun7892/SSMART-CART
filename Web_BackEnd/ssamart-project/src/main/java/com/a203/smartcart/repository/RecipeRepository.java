package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Product;
import com.a203.smartcart.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByCuisineSeq(int cuisineSeq);

    Recipe findBySeq(int seq);
}
