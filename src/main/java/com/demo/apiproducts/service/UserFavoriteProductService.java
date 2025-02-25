package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.dtos.response.ResponseProductFavDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.model.UserFavoriteProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFavoriteProductService {

   private final UserFavoriteProductRepository userFavoriteProductRepository;
   private final ProductRepository productRepository;
   private final RlProductMapper productMapper;

   public ResponseProductFavDTO addAndRemoveFavoriteProduct(Long idProduct, String userId) {
      RlProduct rlProduct = productRepository.findById(idProduct).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + idProduct + " does not exist.")
                                       .build());
      if (Boolean.TRUE.equals(userFavoriteProductRepository.existsFavoriteProductForUser(parseLong(userId), idProduct))) {
         UserFavoriteProduct userFavoriteProduct = userFavoriteProductRepository.findByUserIdAndIdProduct(parseLong(userId), idProduct);
         userFavoriteProduct.setDeletedAt(new Date());
         userFavoriteProductRepository.save(userFavoriteProduct);

         ResponseProductFavDTO responseProductFavDTO = productMapper.toResponseProductFavDTO(rlProduct);
         responseProductFavDTO.setFavorite(false);

         return responseProductFavDTO;

      } else {
         UserFavoriteProduct userFavoriteProduct = UserFavoriteProduct
                 .builder()
                 .idUser(parseLong(userId))
                 .rlProduct(rlProduct)
                 .build();

         userFavoriteProductRepository.save(userFavoriteProduct);
      }

      ResponseProductFavDTO responseProductFavDTO = productMapper.toResponseProductFavDTO(rlProduct);
      responseProductFavDTO.setFavorite(true);
      return responseProductFavDTO;
   }
}
