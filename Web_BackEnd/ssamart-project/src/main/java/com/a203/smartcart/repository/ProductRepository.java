package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 상품명을 포함한 상품을 검색
    Page<Product> findByNameContaining(String name, Pageable pageable);

    // 상품 시퀀스로 상품 검색
    Product findBySeq(int seq);

    // 바코드로 찾기
    Product findByBarcode(String barcode);

    // 할인 하는데 0.3 이상만 페이지로 가져오기
    Page<Product> findByDiscountRateGreaterThanEqualOrderByDiscountRateDesc(Float rate, Pageable pageable);

}
