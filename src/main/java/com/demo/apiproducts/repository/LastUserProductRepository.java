package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlLastUserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LastUserProductRepository extends JpaRepository <RlLastUserProduct, Long> {

   @Query("SELECT l FROM RlLastUserProduct l WHERE l.idUser = :userId")
   RlLastUserProduct findByUserId(@Param("userId") Long userId);

}
