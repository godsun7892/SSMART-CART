package com.a203.smartcart.model.dto;

import lombok.*;
import org.modelmapper.ModelMapper;

@Getter @Setter
@AllArgsConstructor @ToString
@NoArgsConstructor
public class WishCartProductDto {

    private int seq;

    private String name;

    private String content;

    private int price;

    private String image;

    private Float discountRate;

    private Float avgScore;

    private int quantity;

    private int wishProductSeq;

    private String locate;

    private static final ModelMapper modelMapper = new ModelMapper();

    public WishCartProductDto(int seq, String name, String content, int price, String image, Float discountRate, Float avgScore, int quantity, String locate) {
        this.seq = seq;
        this.name = name;
        this.content = content;
        this.price = price;
        this.image = image;
        this.discountRate = discountRate;
        this.avgScore = avgScore;
        this.quantity = quantity;
        this.locate = locate;
    }

    public WishCartProductDto(int seq, String name, String content, int price, String image, Float discountRate, Float avgScore, int quantity) {
        this.seq = seq;
        this.name = name;
        this.content = content;
        this.price = price;
        this.image = image;
        this.discountRate = discountRate;
        this.avgScore = avgScore;
        this.quantity = quantity;
        // locate 필드는 이 생성자에서 초기화되지 않음
    }

}
