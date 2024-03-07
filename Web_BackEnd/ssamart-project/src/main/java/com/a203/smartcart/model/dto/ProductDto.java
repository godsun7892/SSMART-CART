package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter @ToString
public class ProductDto {

    private String name;

    private String content;

    private int price;

    private String image;

    private Float discountRate;

    private Boolean discount;

    private Float avgScore;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProductDto productDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

}
