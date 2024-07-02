package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.exception.RepeatedProductInFavoritesListException;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.model.UserFavoriteProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFavoriteProductService {

   private final UserFavoriteProductRepository userFavoriteProductRepository;
   private final ProductRepository productRepository;

   public void addFavoriteProduct(Long idProduct, String userId) {
      RlProduct rlProduct = productRepository.findById(idProduct).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + idProduct + " does not exist.")
                                       .build());
      if (Boolean.TRUE.equals(userFavoriteProductRepository.existsFavoriteProductForUser(parseLong(userId), idProduct))) {
         throw new RepeatedProductInFavoritesListException("The product with the ID: " + idProduct + " is already in the user's favorite list.");
      }
      UserFavoriteProduct userFavoriteProduct = UserFavoriteProduct
              .builder()
              .idUser(parseLong(userId))
              .rlProduct(rlProduct)
              .build();

      userFavoriteProductRepository.save(userFavoriteProduct);

   }
}
