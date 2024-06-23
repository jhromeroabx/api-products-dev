package com.demo.apiproducts.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"idProduct", "name", "productType", "currency", "price", "images", "isFavorite", "description", "largeDescription"})
public class ResponseProductByIdDTO implements Serializable {

   private Long idProduct;
   private String name;
   private ResponseProductTyDTO productType;
   private Character currency;
   private Double price;
   private List <ResponseProductImageDTO> images;
   @JsonProperty("isFavorite")
   private boolean isFavorite = false;
   private String description;
   private String largeDescription;


}
