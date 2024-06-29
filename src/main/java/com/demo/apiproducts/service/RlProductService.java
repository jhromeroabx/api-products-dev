package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.dto.ProductResponse;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductImageMapper;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import com.demo.apiproducts.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
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

   public ProductResponse getProducts(Integer idProductType, String productName, boolean onlyFavorite, Integer page, Integer size) {
      Specification <RlProduct> spec = Specification.where(ProductSpecifications.hasProductType(idProductType))
                                                    .and(ProductSpecifications.hasProductName(productName))
                                                    .and(ProductSpecifications.isFavorite(onlyFavorite));
      Page <RlProduct> productsPage = productRepository.findAll(spec, PageRequest.of(page - 1, size));
      return new ProductResponse(productsPage, page, size);
   }

}
