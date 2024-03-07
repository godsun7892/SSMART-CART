package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Entity
@Table(name="paymethod")
@Getter
@Setter
@ToString
public class Paymethod extends BaseEntity {

    @Id
    @Column(name="paymethod_seq")
    @Comment("결제수단 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_seq")
    @Comment("회원 일련번호")
    private Member member;

    @Column(name="paymethod_billingKey", length = 50)
    @Comment("결제수단 결제키")
    private String billingKey;

    @Column(name="paymethod_customerKey", length = 50)
    @Comment("결제수단 회원키")
    private String customerKey;

    @Column(name="paymethod_cardType", length = 20)
    @Comment("결제수단 카드타입")
    private String cardType;

    @Column(name="paymethod_ownerType", length = 20)
    @Comment("결제수단 회원키")
    private String ownerType;

}
