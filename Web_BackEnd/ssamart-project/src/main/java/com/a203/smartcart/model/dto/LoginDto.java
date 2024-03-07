package com.a203.smartcart.model.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private int seq;
    private String name;
    private String token;

}
