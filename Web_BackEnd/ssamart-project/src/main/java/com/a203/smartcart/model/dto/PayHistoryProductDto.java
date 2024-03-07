package com.a203.smartcart.model.dto;

import lombok.Data;

@Data
public class PayHistoryProductDto {

    private int productSeq;
    private String name;
    private int price;
    private int discountPrice;
    private int quantity;

}
