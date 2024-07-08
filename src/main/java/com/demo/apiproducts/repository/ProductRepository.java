package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface ProductRepository extends JpaRepository <RlProduct, Long> {

    @Modifying
    @Query("UPDATE RlProduct p SET p.dailyOffer = false WHERE p.dailyOffer = true")
    void deactivateCurrentDailyOffer();

    @Modifying
    @Query("UPDATE RlProduct p SET p.dailyOffer = :dailyOffer WHERE p.id = :id")
    void updateDailyOfferById(@Param("id") Long id, @Param("dailyOffer") boolean dailyOffer);

    @Query("SELECT lup.id FROM LastUserProduct lup WHERE lup.user.id = :userId AND lup.deletedAt IS NULL")
    Long findLastVisitedProductId(@Param("userId") Long userId);

    @Query("SELECT p FROM RlProduct p WHERE p.dailyOffer = true")
    RlProduct findDailyOffer();

}

