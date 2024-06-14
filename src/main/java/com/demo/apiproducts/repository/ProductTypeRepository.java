package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.RlProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<RlProductType, Long> {

}
