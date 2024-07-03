package com.demo.apiproducts.specifications;

import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.model.UserFavoriteProduct;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
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

   public static Specification <RlProduct> isFavoriteForUser(String userId) {
      return (root, query, criteriaBuilder) -> {
         if (userId == null) {
            return null;
         }
         Subquery <Long> subquery = query.subquery(Long.class);
         Root <UserFavoriteProduct> subRoot = subquery.from(UserFavoriteProduct.class);
         subquery.select(subRoot.get("rlProduct").get("id"))
                 .where(criteriaBuilder.equal(subRoot.get("idUser"), Long.parseLong(userId)));

         return criteriaBuilder.in(root.get("id")).value(subquery);
      };
   }

}
