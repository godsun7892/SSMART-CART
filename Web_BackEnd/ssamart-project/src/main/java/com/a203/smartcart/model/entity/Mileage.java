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
@Table(name="mileage")
@Getter
@Setter
@ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class Mileage {

    @Id
    @Column(name="mileage_seq")
    @Comment("마일리지 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_seq")
    @Comment("회원 일련번호")
    private Member member;

    @Column(name="mileage_change")
    @Comment("마일리지 사용 및 적립")
    private int change;

    @Column(name="mileage_content")
    @Comment("마일리지 사용 및 적립 내역")
    private String content;

    @LastModifiedDate
    @Comment("마일리지 사용 및 적립 일자")
    @Column(name = "mileage_date")
    private LocalDateTime date;

}
