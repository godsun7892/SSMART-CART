package com.a203.smartcart.model.entity;

import com.a203.smartcart.model.constant.ProductSellStatus;
import com.a203.smartcart.model.dto.ProductFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name="product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity {


    @Id
    @Column(name="product_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq; // 상품 코드

    @Column(name="product_barcode", unique=true, length = 100)
    private String barcode; // 상품 바코드

    @Column(name="product_name", length = 100)
    private String name; // 상품명

    @Column(name="product_content")
    private String content; // 상품 상세설명

    @Column(name="product_stock")
    private int stock; // 상품 재고

    @Column(name="product_price")
    private int price; // 상품 가격

    @Column(name="product_image")
    private String image; // 상품 이미지

    @Column(name = "product_discount_rate")
    private Float discountRate; // 상품 할인율

    @Column(name = "product_discount")
    private Boolean discount; // 상품 할인유무

    @Column(name = "product_avg_score")
    private Float avgScore; // 상품 평균 별점

    @Column(name = "product_sales")
    private int sales; // 상품 판매량

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus; // 상품 판매 상태

    @ManyToOne
    @JoinColumn(name = "category_seq", referencedColumnName = "category_seq")
    private Category category;

    public void updateFromDto(ProductFormDto productFormDto, Category category) {
        this.setBarcode(productFormDto.getBarcode());
        this.setName(productFormDto.getName());
        this.setContent(productFormDto.getContent());
        this.setStock(productFormDto.getStock());
        this.setPrice(productFormDto.getPrice());
        this.setImage(productFormDto.getImage());
        this.setDiscountRate(productFormDto.getDiscountRate());
        this.setDiscount(productFormDto.getDiscount());
        this.setAvgScore(productFormDto.getAvgScore());
        this.setCategory(this.category); // 카테고리 업데이트
    }
}
