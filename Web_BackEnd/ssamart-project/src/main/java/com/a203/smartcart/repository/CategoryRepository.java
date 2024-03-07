package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // 카테고리 이름으로 검색
    List<Category> findByNameContaining(String name);

    @Query("select c.location from Category c")
    List<String> findAllLocations();

}
