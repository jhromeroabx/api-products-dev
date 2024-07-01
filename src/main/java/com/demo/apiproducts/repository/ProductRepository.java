package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository <RlProduct, Long> {

    @Modifying
    @Query("UPDATE RlProduct p SET p.dailyOffer = false WHERE p.dailyOffer = true")
    void deactivateCurrentDailyOffer();

}