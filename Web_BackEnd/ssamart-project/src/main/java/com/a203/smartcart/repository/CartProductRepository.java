package com.a203.smartcart.repository;

import com.a203.smartcart.model.dto.WishCartProductDto;
import com.a203.smartcart.model.entity.Cartproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartProductRepository extends JpaRepository<Cartproduct, Integer> {

    @Query("SELECT new com.a203.smartcart.model.dto.WishCartProductDto(p.seq, p.name, p.content, p.price, p.image, p.discountRate, p.avgScore, cp.quantity) " +
            "FROM Cartproduct cp " +
            "JOIN cp.product p " +
            "WHERE cp.cart.seq = :seq")
    List<WishCartProductDto> findWishCartProductDtoList(int seq);

    @Transactional
    @Modifying
    @Query("UPDATE Cartproduct cp SET cp.quantity = cp.quantity + 1 WHERE cp.seq = :seq")
    int plusCartproduct(int seq);

    @Transactional
    @Modifying
    @Query("UPDATE Cartproduct cp SET cp.quantity = cp.quantity - 1 WHERE cp.seq = :seq")
    int minusCartproduct(int seq);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cartproduct cp WHERE cp.cart.seq = :cartSeq")
    void deleteByCartSeq(int cartSeq);

}
