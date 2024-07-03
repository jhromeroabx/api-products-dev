package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.dtos.response.ResponseGetAllProductsDTO;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.dtos.response.ResponseProductDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductImageMapper;
import com.demo.apiproducts.mapper.RlProductMapper;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import com.demo.apiproducts.specifications.ProductSpecifications;
import java.util.List;
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
   private final RlProductMapper productMapper;

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

   public ResponseGetAllProductsDTO getAllProductsDTO(Integer idProductType, String productName, boolean onlyFavorite, Integer page, Integer size, String userId) {
      Specification <RlProduct> spec = Specification.where(ProductSpecifications.hasProductType(idProductType))
                                                    .and(ProductSpecifications.hasProductName(productName));

      if (onlyFavorite) {
         spec = spec.and(ProductSpecifications.isFavoriteForUser(userId));
      }

      Page <RlProduct> productsPage = productRepository.findAll(spec, PageRequest.of(page - 1, size));
      List <ResponseProductDTO> products = productsPage.getContent().stream()
                                                       .map(productMapper::toResponseProductDTO)
                                                       .toList();

      for (ResponseProductDTO product : products) {
         if (Boolean.TRUE.equals(userFavoriteProductRepository.existsFavoriteProductForUser(Long.parseLong(userId), product.getIdProduct()))) {
            product.setFavorite(true);
         }
      }

      return ResponseGetAllProductsDTO.builder()
                                      .page(page)
                                      .size(size)
                                      .totalPages(productsPage.getTotalPages())
                                      .totalProducts(productsPage.getTotalElements())
                                      .products(products)
                                      .build();
   }

}
