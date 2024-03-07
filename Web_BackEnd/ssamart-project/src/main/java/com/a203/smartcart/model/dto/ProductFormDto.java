package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.constant.ProductSellStatus;
import com.a203.smartcart.model.entity.Category;
import com.a203.smartcart.model.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFormDto {

    @NotEmpty
    private String barcode;

    @NotEmpty
    private String name;

    @NotEmpty
    private String content;

    @NotNull
    private Integer stock;

    @NotNull
    private Integer price;

    private String image;

    @NotNull
    private Float discountRate;

    @NotNull
    private Boolean discount;

    @NotNull
    private Float avgScore;

    private ProductSellStatus productSellStatus;

    private int categorySeq; // Category 정보를 위한 DTO 필드 추가

    public Product createProduct(Category categoryEntity) {
        Product product = new Product();
        product.setBarcode(this.barcode);
        product.setName(this.name);
        product.setContent(this.content);
        product.setStock(this.stock);
        product.setPrice(this.price);
        product.setImage(this.image);
        product.setDiscountRate(this.discountRate);
        product.setDiscount(this.discount);
        product.setAvgScore(this.avgScore);
        product.setProductSellStatus(this.productSellStatus);
        product.setCategory(categoryEntity);
        return product;
    }


}
