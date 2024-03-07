package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    // 회원 시퀀스로 찜 목록 찾기
    ArrayList<Wishlist> findByMemberSeq(int seq);

//    Wishlist findById(int seq);

}
