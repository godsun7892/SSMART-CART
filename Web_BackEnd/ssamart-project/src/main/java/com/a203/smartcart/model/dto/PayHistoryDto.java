package com.a203.smartcart.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PayHistoryDto {

    private String date;

    private int receiptSeq;
    
    List<PayHistoryProductDto> payHistoryProductDtoList;

}
