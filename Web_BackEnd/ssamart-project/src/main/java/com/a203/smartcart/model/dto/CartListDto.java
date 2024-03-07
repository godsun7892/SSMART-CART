package com.a203.smartcart.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class CartListDto {

    private List<WishCartProductDto> wishCartProductDtoList;

    private int cartSeq;

}
