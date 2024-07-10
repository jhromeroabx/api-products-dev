package com.demo.apiproducts.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RequestCreateProduct implements Serializable {
   @NotBlank
   private String name;
   @NotNull
   private Long idProductType;
   @NotBlank
   private Character currency;
   @NotNull
   private Double price;
   @NotBlank
   private String description;
   @NotBlank
   private String largeDescription;
   @NotNull
   private List <RequestCreateProductImage> images;
   @NotNull
   private List <RequestCreateProductColor> colors;
}
