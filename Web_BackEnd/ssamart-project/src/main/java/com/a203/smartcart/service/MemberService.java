package com.a203.smartcart.service;

import com.a203.smartcart.config.jwt.JwtUtil;
import com.a203.smartcart.model.dto.LoginDto;
import com.a203.smartcart.model.dto.LoginFormDto;
import com.a203.smartcart.model.dto.MemberDto;
import com.a203.smartcart.model.entity.Member;
import com.a203.smartcart.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;


    // 회원가입
    public Member saveMember(Member member) {

        //중복 체크
        Member findMember = memberRepository.findByUserId(member.getUserId());
        System.out.println("saveMember 수행중");
        if(findMember != null) {
            return null;
        }
        return memberRepository.save(member);
    }

    @Transactional
    public LoginDto login(LoginFormDto loginFormDto) {
        String userId = loginFormDto.getUserId();
        String password = loginFormDto.getPassword();

        Member member = memberRepository.findByUserId(userId);
        if(member == null)
            throw new UsernameNotFoundException("아이디가 존재하지 않습니당ㅠ");

        if(!encoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니당");
        }

        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        String accessToken = jwtUtil.createAccessToken(memberDto);
        LoginDto loginDto = new LoginDto(member.getSeq(), member.getName(), accessToken);
        return loginDto;
    }

    @Transactional
    public LoginDto fingerPrintLogin(String userId){
        Member member = memberRepository.findByUserId(userId);
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        String accessToken = jwtUtil.createAccessToken(memberDto);
        LoginDto loginDto = new LoginDto(member.getSeq(), member.getName(), accessToken);
        return loginDto;
    }
}
