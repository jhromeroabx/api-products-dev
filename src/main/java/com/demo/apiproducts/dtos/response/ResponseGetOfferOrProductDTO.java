package com.demo.apiproducts.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetOfferOrProductDTO implements Serializable {
   private Long idProduct;
   private String name;
   private ResponseProductTyDTO productType;
   private Character currency;
   private Double price;
   private List <ResponseProductImageDTO> images;
   @JsonProperty("isFavorite")
   private boolean isFavorite = false;
   private boolean isDailyOffer = false;
   private String description;
}
