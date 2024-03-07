package com.a203.smartcart.repository;
import com.a203.smartcart.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 아이디로 회원 찾기
    Member findByUserId(String userId);
    // member 시퀀스로 회원 찾기
    Member findBySeq(int seq);

}
