package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedDishDto {

    private String cuisineImage;

    private String cuisineName;

    private String cuisineStepImage;

    private List<Product> productList;

}
