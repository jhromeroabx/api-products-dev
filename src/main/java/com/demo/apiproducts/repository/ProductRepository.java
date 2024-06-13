package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<RlProduct, Long> {
}