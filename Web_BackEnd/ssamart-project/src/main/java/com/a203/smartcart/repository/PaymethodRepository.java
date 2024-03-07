package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Paymethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymethodRepository extends JpaRepository<Paymethod, Integer> {

    List<Paymethod> findByMember_Seq(int memSeq);

}
