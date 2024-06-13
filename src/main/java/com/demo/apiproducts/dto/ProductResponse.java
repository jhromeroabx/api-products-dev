package com.demo.apiproducts.dto;

import com.demo.apiproducts.model.RlProduct;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class ProductResponse {
   private int page;
   private int size;
   private int totalPages;
   private long totalProducts;
   private List <ProductDTO> products;

   public ProductResponse(Page <RlProduct> productPage, int page, int size) {
      this.page = page;
      this.size = size;
      this.totalPages = productPage.getTotalPages();
      this.totalProducts = productPage.getTotalElements();
      this.products = productPage.stream().map(ProductDTO::new).collect(Collectors.toList());
   }
}
