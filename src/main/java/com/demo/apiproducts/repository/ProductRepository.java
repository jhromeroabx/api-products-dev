package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface ProductRepository extends JpaRepository <RlProduct, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE rl_api_products.rl_product SET daily_offer = false WHERE daily_offer = true")
    void deactivateCurrentDailyOffer();

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE rl_api_products.rl_product SET daily_offer = ?2 WHERE id_rl_product = ?1")
    void updateDailyOfferById(Long id, boolean dailyOffer);

}

