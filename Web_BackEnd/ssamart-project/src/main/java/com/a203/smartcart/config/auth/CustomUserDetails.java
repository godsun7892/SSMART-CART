package com.a203.smartcart.config.auth;

import com.a203.smartcart.model.dto.MemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 User정보가 있어야 됨
// User오브젝트타입 => UserDetails 타입 객체
@Getter @Setter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final MemberDto memberDto;

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(() -> "ROLE_" + memberDto.getRole().toString());
        return collect;
    }

    @Override
    public String getPassword() {
//        if(member == null) {
//            return null;
//        }
        return memberDto.getPassword();
    }

    @Override
    public String getUsername() {
//        if(member == null) {
//            return null;
//        }
        return memberDto.getUserId();
    }

    // 니 계정 만료 됬니?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 니 계정 잠겼니?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 니 계정의 비밀번호가 기간이 지났니?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 니 계정이 활성화 되어있니?
    @Override
    public boolean isEnabled() {

        // 우리 사이트에 로그인 안한지 1년이 됬다면
        // 니 게정 비활성화

        return true;
    }
}
