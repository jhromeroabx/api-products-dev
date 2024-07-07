package com.demo.apiproducts.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateProductImage implements Serializable {

   @NotBlank
   private String link;
   @NotBlank
   private String provider;
   @NotNull
   private Boolean principal;
}

