package com.a203.smartcart.model.entity;

import com.a203.smartcart.model.constant.Role;
import com.a203.smartcart.model.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(name="mem_seq")
    @Comment("회원 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Comment("회원 아이디")
    @Column(name="mem_id", unique=true, length = 20)
    private String userId;

    @Comment("회원 비밀번호")
    @Column(name="mem_password", length = 100)
    private String password;

    @Comment("회원 이름")
    @Column(name="mem_name", length = 20)
    private String name;

    @Comment("회원 이메일")
    @Column(name="mem_email", length = 20)
    private String email;

    @Comment("회원 포인트")
    @Column(name="mem_point")
    private int point;

    @Comment("회원 권한")
    @Column(name="mem_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Comment("회원 탈퇴여부")
    @Column(name="mem_is_resign")
    private boolean isResign = false;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setUserId(memberFormDto.getUserId());
        member.setEmail(memberFormDto.getEmail());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

}
