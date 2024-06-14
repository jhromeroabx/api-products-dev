package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RlProductImageRepository extends JpaRepository<RlProductImage, Long> {
}
