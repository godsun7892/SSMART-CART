package com.a203.smartcart.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
@Getter  @Setter
@EntityListeners(value = {AuditingEntityListener.class})
@ToString
@NoArgsConstructor
public class Wishlist extends BaseEntity {

    @Id
    @Column(name="wishlist_seq")
    @Comment("결제수단 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_seq")
    @Comment("회원 일련번호")
    private Member member;

    @Column(name="wishlist_total_price")
    @Comment("찜목록 총 가격")
    private int total_price = 0;

    @Column(name="wishlist_name", length = 50)
    @Comment("찜목록 이름")
    private String name;

    public Wishlist(String name, Member member){
        this.name = name;
        this.member = member;
    }
}
