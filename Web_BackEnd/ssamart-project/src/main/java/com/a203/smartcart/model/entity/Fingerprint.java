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
@Table(name="fingerprint")
@Getter
@Setter
@ToString
@EntityListeners(value = {AuditingEntityListener.class})
public class Fingerprint {

    @Id
    @Column(name="fingerprint_seq")
    @Comment("지문 일련번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @Column(name="fingerprint_key")
    @Comment("지문 정보")
    private String key;

    @CreatedDate
    @Comment("지문 등록 일자")
    @Column(name = "fingerprint_date", updatable = false)
    private LocalDateTime date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_seq")
    @Comment("회원 일련번호")
    private Member member;

}
