package com.demo.apiproducts.dtos.response;

import com.demo.apiproducts.dtos.request.RequestHighProduct;
import com.demo.apiproducts.model.RlProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseHighProduct implements Serializable  {
   private Long idProduct;
   private String name;
   private RlProductType productType;
   private Character currency;
   private Double price;
   private List<ResponseProductImageDTO>images;
   @JsonProperty("isFavorite")
   private boolean isFavorite = false;
   private String description;
   private String largeDescription;
}
