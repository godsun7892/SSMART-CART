package com.a203.smartcart.repository;

import com.a203.smartcart.model.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {

    @Query("SELECT MAX(c.seq) FROM Cuisine c")
    int findMaxSeq();

    Cuisine findBySeq(int seq);

}
