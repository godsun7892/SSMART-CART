package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecepitRepository extends JpaRepository<Receipt, Integer> {

    List<Receipt> findByMember_Seq(int memSeq);
}
