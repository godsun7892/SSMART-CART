package com.a203.smartcart.repository;

import com.a203.smartcart.model.dto.WishCartProductDto;
import com.a203.smartcart.model.entity.Wishproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WishProductRepository extends JpaRepository<Wishproduct, Integer> {

    @Query("SELECT wp FROM Wishproduct wp JOIN FETCH wp.product WHERE wp.wishlist.seq = :wishListSeq")
    List<Wishproduct> findWishproductList(@Param("wishListSeq") int wishListSeq);

    Wishproduct findBySeq(int seq);

    int countByWishlistSeq(int wishlistSeq);

    @Transactional
    @Modifying
    @Query("UPDATE Wishproduct w SET w.quantity = w.quantity + 1 WHERE w.seq = :seq")
    int plusWishproduct(int seq);

    @Transactional
    @Modifying
    @Query("UPDATE Wishproduct w SET w.quantity = w.quantity - 1 WHERE w.seq = :seq")
    int minusWishproduct(int seq);

    @Transactional
    @Modifying
    @Query("DELETE FROM Wishproduct wp WHERE wp.wishlist.seq = :wishlistSeq")
    void deleteByWishlistSeq(int wishlistSeq);

}
