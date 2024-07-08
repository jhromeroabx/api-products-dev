package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductImageMapper;
import com.demo.apiproducts.mapper.RlProductMapper;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RlProductService {

   private final ProductRepository productRepository;
   private final RlProductTypeMapper productTypeMapper;
   private final UserFavoriteProductRepository userFavoriteProductRepository;
   private final RlProductImageMapper productImageMapper;
   private final RlProductMapper productMapper; // Nuevo mapper

   public ResponseProductByIdDTO getProductDTOById(String userId, Long idProduct) {
      RlProduct productModel = productRepository.findById(idProduct).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + idProduct + " does not exist.")
                                       .build());

      ResponseProductByIdDTO productDTO = ResponseProductByIdDTO.builder()
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
      if (Boolean.TRUE.equals(userFavoriteProductRepository.existsFavoriteProductForUser(parseLong(userId), idProduct))) {
         productDTO.setFavorite(true);
      }

      return productDTO;
   }

   @Transactional
   public ResponseProductByIdDTO putDailyOffer(String userId,Long idProduct) {
      productRepository.deactivateCurrentDailyOffer();
      RlProduct productModel = productRepository.findById(idProduct).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + idProduct + " does not exist.")
                                       .build());
      if (!productModel.getDailyOffer()) {
         productRepository.updateDailyOfferById(idProduct, true);
      }
      ResponseProductByIdDTO productDTO = productMapper.toResponseProductByIdDTO(productModel);

      return productDTO;
   }

   public ResponseProductByIdDTO getDailyOfferOrLastUserProduct(String userId) {
      Long userIdLong = Long.parseLong(userId);
      Long lastVisitedProductId = productRepository.findLastVisitedProductId(userIdLong);
      if (lastVisitedProductId != null) {
         RlProduct productModel = productRepository.findById(lastVisitedProductId).orElseThrow(
                 () -> IdNotFoundException.builder()
                                          .message("El producto con el ID: " + lastVisitedProductId+ " no existe.")
                                          .build());
         ResponseProductByIdDTO productDTO = productMapper.toResponseProductByIdDTO(productModel);
         return productDTO;
      } else {
         RlProduct dailyOfferProduct = productRepository.findDailyOffer();
         ResponseProductByIdDTO productDTO = productMapper.toResponseProductByIdDTO(dailyOfferProduct);
         return productDTO;
      }
   }

}


