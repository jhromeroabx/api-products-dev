package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.dtos.request.RequestHighProduct;
import com.demo.apiproducts.dtos.response.ResponseHighProduct;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductImageMapper;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.model.RlProductType;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.ProductTypeRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RlProductService {

   private final ProductRepository productRepository;
   private final RlProductTypeMapper productTypeMapper;
   private final ProductTypeRepository productTypeRepository;
   private final UserFavoriteProductRepository userFavoriteProductRepository;
   private final RlProductImageMapper productImageMapper;

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

   public ResponseHighProduct createProductDTO(RequestHighProduct requestHighProduct) {
      RlProductType rlProductType = productTypeRepository.findById(requestHighProduct.getIdType()).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + requestHighProduct.getIdType() + " does not exist.")
                                       .build());
      if (rlProductType.getDeletedAt() != null) {
         throw new IdNotFoundException("id Not Found Exceptiom");
         }
      RlProduct rlProduct = RlProduct.builder()
                                     .name(requestHighProduct.getName())
                                     .productType(rlProductType).currency(requestHighProduct.getCurrency())
                                     .price(requestHighProduct.getPrice())
                                     .description(requestHighProduct.getDescription())
                                     .largeDescription(requestHighProduct.getLargeDescription()).build();

      productRepository.save(rlProduct);
      return ResponseHighProduct.builder().build();
   }
}