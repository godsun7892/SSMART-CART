package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="cart")
@Getter
@Setter
@ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class Cart {

    @Id
    @Column(name="cart_seq")
    @Comment("장바구니 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_seq")
    @Comment("회원 일련번호")
    private Member member;

    @Column(name="cart_total_price")
    @Comment("장바구니 총 금액")
    private int totalPrice;

    @LastModifiedDate
    @Comment("장바구니 생성일자")
    @Column(name = "cart_date")
    private LocalDateTime date;

    public static Cart createCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }

}
