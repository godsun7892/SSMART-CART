package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="receipt")
@Getter
@Setter
@ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class Receipt {

    @Id
    @Column(name="receipt_seq")
    @Comment("결재내역 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_seq")
    @Comment("회원 일련번호")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_seq")
    @Comment("장바구니 일련번호")
    private Cart cart;

    @Column(name="receipt_method")
    @Comment("회원 결제 수단")
    private String method;

    @Column(name="receipt_totalAmount")
    @Comment("회원 결제 금액")
    private int totalAmount;

    @CreatedDate
    @Comment("회원 결제 일자")
    @Column(name = "receipt_date", updatable = false)
    private LocalDateTime date;

}
