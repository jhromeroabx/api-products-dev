package com.demo.apiproducts.dto;

import com.demo.apiproducts.model.RlProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeDTO {
   private Long idType;
   private String description;

   public ProductTypeDTO(RlProductType productType) {
      this.idType = productType.getId();
      this.description = productType.getName();
   }
}
