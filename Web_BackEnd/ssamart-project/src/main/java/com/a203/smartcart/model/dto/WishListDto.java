package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.entity.Wishlist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter @Setter @ToString
@RequiredArgsConstructor
public class WishListDto {

    private int seq;

    private String name;

    private int total_price;

    private String regTime;

    private String updateTime;

    private String img;

    private int num;

    private String productName;

}
