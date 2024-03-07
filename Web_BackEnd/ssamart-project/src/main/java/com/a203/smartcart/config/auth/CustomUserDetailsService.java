package com.a203.smartcart.config.auth;

import com.a203.smartcart.model.dto.MemberDto;
import com.a203.smartcart.model.entity.Member;
import com.a203.smartcart.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 loC되어 인쓴 loadUserByUsername 함수가 실행
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    // 여기서 유저 확인
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService의 loadUserByUsername()");

        Member member = memberRepository.findByUserId(userId);
        if (member == null)
            throw new UsernameNotFoundException("해당하는 유저가 없습니다ㅠ");

        MemberDto memberDto = modelMapper.map(member, MemberDto.class);

        return new CustomUserDetails(memberDto);

        // Authentication 타입으로 UserDetails가 들어가야 한다.
        // 그래서 UserDetails 작업 중


    }
}
