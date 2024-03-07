package com.a203.smartcart.model.dto;

import com.a203.smartcart.model.constant.Role;
import lombok.*;

@Getter @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private int seq;

    private String userId;

    private String password;

    private String email;

    private Role role;

}
