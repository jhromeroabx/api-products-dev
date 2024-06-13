package com.demo.apiproducts.service;

import com.demo.apiproducts.dto.ProductResponse;
import com.demo.apiproducts.model.RlProduct;
import com.demo.apiproducts.repository.ProductRepository;
import com.demo.apiproducts.repository.ProductTypeRepository;
import com.demo.apiproducts.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
   @Autowired
   private ProductRepository productRepository;
   private ProductTypeRepository productTypeRepository;

   public ProductService(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
      this.productRepository = productRepository;
      this.productTypeRepository = productTypeRepository;
   }

   public ProductResponse getProducts(Integer idProductType, String productName, boolean onlyFavorite, Integer page, Integer size) {
      Specification<RlProduct> spec = Specification.where(ProductSpecifications.hasProductType(idProductType))
                                                 .and(ProductSpecifications.hasProductName(productName))
                                                 .and(ProductSpecifications.isFavorite(onlyFavorite));

      Page<RlProduct> productsPage = productRepository.findAll(spec, PageRequest.of(page - 1, size));
      return new ProductResponse(productsPage, page, size);
   }
}
