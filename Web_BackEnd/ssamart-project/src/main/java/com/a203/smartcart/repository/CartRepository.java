package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    // 회원 시퀀스로 장바구니 목록 찾기
    Cart findByMemberSeq(int seq);

}
