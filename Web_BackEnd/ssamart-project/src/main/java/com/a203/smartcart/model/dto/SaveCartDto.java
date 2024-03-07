package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@NoArgsConstructor
public class SaveCartDto {

    private Product product;

    private int CartProductSeq;

}
