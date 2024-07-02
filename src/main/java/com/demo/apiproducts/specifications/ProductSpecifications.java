package com.demo.apiproducts.specifications;

import com.demo.apiproducts.model.RlProduct;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

   public static Specification <RlProduct> hasProductType(Integer idProductType) {
      return (root, query, criteriaBuilder) -> {
         if (idProductType == null) {
            return criteriaBuilder.conjunction();
         }
         return criteriaBuilder.equal(root.get("productType").get("id"), idProductType);
      };
   }

   public static Specification <RlProduct> hasProductName(String productName) {
      return (root, query, criteriaBuilder) -> {
         if (productName == null || productName.isEmpty()) {
            return criteriaBuilder.conjunction();
         }
         return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + productName.toLowerCase() + "%");
      };
   }

}
