package com.a203.smartcart.service;

import com.a203.smartcart.model.dto.RecommendedDishDto;
import com.a203.smartcart.model.entity.Cuisine;
import com.a203.smartcart.model.entity.Product;
import com.a203.smartcart.model.entity.Recipe;
import com.a203.smartcart.repository.CategoryRepository;
import com.a203.smartcart.repository.CuisineRepository;
import com.a203.smartcart.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class EctService {

    private final CuisineRepository cuisineRepository;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    // 랜덤 오메추
    public RecommendedDishDto getRandomCuisine() {

        // 랜덤 요리 뽑기
        int maxCuisineSeq = cuisineRepository.findMaxSeq();
        Random rnd = new Random();
        int randomCuisineSeq = rnd.nextInt(maxCuisineSeq) + 1;
        if(randomCuisineSeq == 1)
            randomCuisineSeq = 2;

        // 뽑은 값으로 요리 찾아서 Dto에 넣기
        Cuisine cuisine = cuisineRepository.findBySeq(randomCuisineSeq);
        RecommendedDishDto recommendedDishDto = new RecommendedDishDto();
        recommendedDishDto.setCuisineImage(cuisine.getImage());
        recommendedDishDto.setCuisineStepImage(cuisine.getStepImage());
        recommendedDishDto.setCuisineName(cuisine.getName());

        //product
        List<Recipe> recipes = recipeRepository.findByCuisineSeq(randomCuisineSeq);
        List<Product> products = new ArrayList<>();
        for (Recipe recipe : recipes) {
            products.add(recipe.getProduct());
        }
        recommendedDishDto.setProductList(products);
        return recommendedDishDto;

    }

    // 모든 로케이션 가져오기
    public List<String> getAllLocations() {
        return categoryRepository.findAllLocations();
    }

}
