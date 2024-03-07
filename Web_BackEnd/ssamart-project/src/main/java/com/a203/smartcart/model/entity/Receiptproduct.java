package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Entity
@Table(name="receipt_product")
@Getter @Setter @ToString
@NoArgsConstructor
public class Receiptproduct {

    @Id
    @Column(name="receipt_product_seq")
    @Comment("결제 상품 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="receipt_seq")
    @Comment("결제내역 일련번호")
    private Receipt receipt;

    @Column(name="product_seq")
    private int productSeq; // 상품 코드

    @Column(name="receipt_product_name", nullable = false)
    private String name; // 상품명

    @Column(name="receipt_product_price", nullable = false)
    private int price; // 상품 가격

    @Column(name="receipt_discount_price", nullable = false)
    private int discountPrice; // 상품 할인 가격

    @Column(name = "receipt_product_quantity", nullable = false)
    private int quantity; // 결제 상품 수량

}
