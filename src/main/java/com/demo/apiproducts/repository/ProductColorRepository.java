package com.demo.apiproducts.repository;


import com.demo.apiproducts.model.RlProductColor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository <RlProductColor, Long>, JpaSpecificationExecutor <RlProductColor> {
   List<RlProductColor> findByProductId(Long productId);
}
