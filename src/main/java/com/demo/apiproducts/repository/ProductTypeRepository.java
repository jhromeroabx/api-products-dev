package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProductType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository <RlProductType, Long> {

   @Query("SELECT r FROM RlProductType r WHERE r.deletedAt IS NULL")
   List <RlProductType> findByDeletedAtIsNull();

}
