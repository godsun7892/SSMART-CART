package com.a203.smartcart.controller;


import com.a203.smartcart.model.dto.LoginDto;
import com.a203.smartcart.model.dto.LoginFormDto;
import com.a203.smartcart.model.dto.MemberFormDto;
import com.a203.smartcart.model.entity.Member;
import com.a203.smartcart.service.MemberService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // 이거 박으면 빈을 주입할 때 오토와이얼드 안박아도 final 같은거 박혀 있으면 자동으로 해줌
public class UserRestController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @PostMapping("/regist")
    @JsonProperty("memberFormDto")
    public ResponseEntity<?> test(@RequestBody MemberFormDto memberFormDto) {

            System.out.println(memberFormDto.toString());

            try {
                Member member = Member.createMember(memberFormDto, passwordEncoder);
                if(memberService.saveMember(member) != null) {
                    System.out.println("회원 가입이 성공하였습니다");
                    return new ResponseEntity<Void>(HttpStatus.CREATED);
                }
                System.out.println("회원 가입이 실패하였습니다");
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }

    }

    @PostMapping("/login")
    public ResponseEntity<?> getMemberProfile(@RequestBody LoginFormDto loginFormDto) {
        LoginDto loginDto = this.memberService.login(loginFormDto);
        return ResponseEntity.status(HttpStatus.OK).body(loginDto);
    }

}

