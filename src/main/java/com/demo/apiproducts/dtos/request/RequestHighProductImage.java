package com.demo.apiproducts.dtos.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestHighProductImage implements Serializable {

   private String link;
   private String provider;
   private Boolean principal;
}

