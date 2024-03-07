package com.a203.smartcart.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BarcodeDto {

    @NotEmpty
    private String barcode;

}
