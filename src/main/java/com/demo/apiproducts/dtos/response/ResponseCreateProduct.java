package com.demo.apiproducts.dtos.response;

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
public class ResponseCreateProduct implements Serializable {

   private Long idProduct;
   private String name;
   private ResponseProductTyDTO productType;
   private Character currency;
   private Double price;
   private List <ResponseProductImageDTO> images;
   private List <ResponseProductColorDTO> colors;
   @JsonProperty("isFavorite")
   private boolean isFavorite = false;
   private String description;
   private String largeDescription;
}
