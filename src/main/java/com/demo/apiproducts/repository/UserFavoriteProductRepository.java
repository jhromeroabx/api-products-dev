package com.demo.apiproducts.repository;

import com.demo.apiproducts.model.UserFavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteProductRepository extends JpaRepository <UserFavoriteProduct, Long> {

   @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM UserFavoriteProduct p WHERE p.idUser = :idUser AND p.id = :idProduct")
   Boolean existsFavoriteProductForUser(@Param("idUser") Long idUser, @Param("idProduct") Long idProduct);
}
