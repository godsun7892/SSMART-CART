package com.a203.smartcart.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PayMethodFormDto {

    private String phoneNumber;
    private String authKey;
    private String customerKey;

}
