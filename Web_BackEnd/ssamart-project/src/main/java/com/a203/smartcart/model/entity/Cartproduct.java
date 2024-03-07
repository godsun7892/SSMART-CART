package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="cart_product")
@Getter
@Setter
@ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class Cartproduct {

    @Id
    @Column(name="cart_product_seq")
    @Comment("장바구니 상품 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_seq")
    @Comment("장바구니 일련번호")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_seq")
    @Comment("상품 일련번호")
    private Product product;

    @Column(name="cart_product_quantity")
    @Comment("장바구니 상품 수량")
    private int quantity;

    public Cartproduct() {

    }

    public Cartproduct(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }
}
