package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "wish_product")
@Getter @Setter @ToString
@NoArgsConstructor
public class Wishproduct {

    @Id
    @Column(name="wish_product_seq")
    @Comment("찜상품 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wishlist_seq")
    @Comment("찜 목록 일련번호")
    private Wishlist wishlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_seq")
    @Comment("상품 일련번호")
    private Product product;

    @Column(name="wish_product_quantity")
    @Comment("찜 상품 수량")
    private int quantity;

}
