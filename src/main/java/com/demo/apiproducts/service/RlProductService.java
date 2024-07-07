package com.demo.apiproducts.service;

import static java.lang.Long.parseLong;

import com.demo.apiproducts.dtos.request.RequestCreateProduct;
import com.demo.apiproducts.dtos.request.RequestCreateProductImage;
import com.demo.apiproducts.dtos.response.ResponseCreateProduct;
import com.demo.apiproducts.dtos.response.ResponseGetAllProductsDTO;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.dtos.response.ResponseProductDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.exception.MultipleMainImagesException;
import com.demo.apiproducts.exception.NoMainImageException;
import com.demo.apiproducts.mapper.RlProductImageMapper;
import com.demo.apiproducts.mapper.RlProductMapper;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.model.RlProductImage;
import com.demo.apiproducts.model.RlProductType;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.ProductTypeRepository;
import com.demo.apiproducts.repository.UserFavoriteProductRepository;
import com.demo.apiproducts.specifications.ProductSpecifications;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RlProductService {

   private final ProductRepository productRepository;
   private final RlProductTypeMapper productTypeMapper;
   private final ProductTypeRepository productTypeRepository;
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

   @Transactional
   public ResponseProductByIdDTO putDailyOffer(Long idProduct) {
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

   public ResponseCreateProduct createProductDTO(RequestCreateProduct requestCreateProduct) {
      RlProductType rlProductType = productTypeRepository.findById(requestCreateProduct.getIdType()).orElseThrow(
              () -> IdNotFoundException.builder()
                                       .message("The product with the ID: " + requestCreateProduct.getIdType() + " does not exist.")
                                       .build());
      if (rlProductType.getDeletedAt() != null) {
         throw new IdNotFoundException("id Not Found Exception");
      }

      List <RequestCreateProductImage> productImages = requestCreateProduct.getImages();

      var principalCount = productImages.stream()
                                        .filter(RequestCreateProductImage::getPrincipal)
                                        .count();

      if (principalCount > 1) {
         throw new MultipleMainImagesException("Only one image can be main.");
      } else if (principalCount == 0) {
         throw new NoMainImageException("There must be a main image.");
      }

      RlProduct rlProduct = productMapper.toModel(requestCreateProduct);

      List <RlProductImage> images = new ArrayList <>();
      for (RequestCreateProductImage requestHighProductImage : requestCreateProduct.getImages()) {
         RlProductImage rlProductImage = productImageMapper.toModel(requestHighProductImage);
         rlProductImage.setProduct(rlProduct);
         images.add(rlProductImage);
      }
      rlProduct.setProductImages(images);
      productRepository.save(rlProduct);

      ResponseCreateProduct responseCreateProduct = productMapper.toResponseCreateProduct(rlProduct);
      responseCreateProduct.setFavorite(false);
      return responseCreateProduct;
   }

   public ResponseGetAllProductsDTO getAllProductsDTO(Integer idProductType, String productName, boolean onlyFavorite, Integer page, Integer size, String userId) {
      Specification <RlProduct> spec = Specification.where(ProductSpecifications.hasProductType(idProductType))
                                                    .and(ProductSpecifications.hasProductName(productName))
                                                    .and(ProductSpecifications.isFavoriteForUser(userId, onlyFavorite));

      Page <RlProduct> productsPage = productRepository.findAll(spec, PageRequest.of(page - 1, size));
      List <ResponseProductDTO> products = productsPage.getContent().stream()
                                                       .map(productMapper::toResponseProductDTO)
                                                       .toList();

      List <Long> favoriteProductIds = userFavoriteProductRepository.findFavoriteProductIdsByUserId(Long.parseLong(userId));
      for (ResponseProductDTO product : products) {
         if (favoriteProductIds.contains(product.getIdProduct())) {
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