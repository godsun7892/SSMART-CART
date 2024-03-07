package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Receiptproduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecepitproductRepository extends JpaRepository<Receiptproduct, Integer> {

    List<Receiptproduct> findByReceipt_Seq(int receiptSeq);

}
