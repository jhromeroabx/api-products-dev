package com.demo.apiproducts.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO implements Serializable {

   private Long idProduct;
   private String name;
   private ResponseProductTyDTO productType;
   private Character currency;
   private Double price;
   private String image;
   @JsonProperty("isFavorite")
   private boolean isFavorite;
   private String description;

}
