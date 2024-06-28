package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductImageMapper;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RlProductService {

   private final ProductRepository productRepository;
   private final RlProductTypeMapper productTypeMapper;
   private final UserFavoriteProductRepository userFavoriteProductRepository;
   private final RlProductImageMapper productImageMapper;

   public ResponseProductByIdDTO getProductDTOById(String userId, Long idProduct) {
      RlProduct productModel = productRepository.findById(idProduct).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + idProduct + " does not exist.")
                                       .build());
      ResponseProductByIdDTO productDTO = toResponseProductByIdDTO(productModel);
      if (Boolean.TRUE.equals(userFavoriteProductRepository.existsFavoriteProductForUser(parseLong(userId), idProduct))) {
         productDTO.setFavorite(true);
      }

      return productDTO;
   }

   public ResponseProductByIdDTO putDailyOffer(Long idProduct) {
      String userId = getCurrentUserId();
      RlProduct productModel = productRepository.findById(idProduct).orElseThrow(
              () -> IdNotFoundException.builder()
                      .message("The product with the ID: " + idProduct + " does not exist.")
                      .build());
      ResponseProductByIdDTO productDTO = toResponseProductByIdDTO(productModel);
      productRepository.deactivateCurrentDailyOffer();
      productModel.setDailyOffer(true);
      if (Boolean.TRUE.equals(userFavoriteProductRepository.existsFavoriteProductForUser(Long.parseLong(userId), idProduct))) {
         productDTO.setFavorite(true);
      }
      productRepository.save(productModel);

      return productDTO;
   }

   private ResponseProductByIdDTO toResponseProductByIdDTO(RlProduct productModel) {
      return ResponseProductByIdDTO.builder()
                                   .idProduct(productModel.getId())
                                   .name(productModel.getName())
                                   .productType(productTypeMapper.toResponseProductTyDTO(productModel.getProductType()))
                                   .currency(productModel.getCurrency())
                                   .price(productModel.getPrice())
                                   .images(productModel.getProductImages().stream().map(productImageMapper::toDTO).toList())
                                   .isFavorite(false)
                                   .description(productModel.getDescription())
                                   .largeDescription(productModel.getLargeDescription())
                                   .build();
   }

   private String getCurrentUserId() {
      org.springframework.security.core.userdetails.User principal =
              (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return principal.getUsername();
   }
}


