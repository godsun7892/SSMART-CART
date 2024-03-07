package com.a203.smartcart.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class}) // 이 클래스는 Spring Data JPA에서 제공하는 이벤트 리스너로 엔티티의 영속, 수정 이벤트를 감지하는 역할을 한다.
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity {

//        @CreatedBy // 생성한 사람 자동 등록
//        @Column(updatable = false)
//        private String createBy;
//
//        @LastModifiedBy
//        private String modifiedBy;

}
