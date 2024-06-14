package com.demo.apiproducts.dto;

import com.demo.apiproducts.model.RlProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
   private Long idProduct;
   private String name;
   private ProductTypeDTO productType;
   private String currency;
   private String price;
   private String image;
   private boolean isFavorite;
   private String description;

   public ProductDTO(RlProduct product) {
      this.idProduct = product.getId();
      this.name = product.getName();
      this.productType = new ProductTypeDTO(product.getProductType());
      this.currency = product.getCurrency();
      this.price = product.getPrice();
      this.image = product.getProductImages().isEmpty() ? null : product.getProductImages().get(0).getProviderLink();
      this.isFavorite = false;
      this.description = product.getDescription();
   }
}
