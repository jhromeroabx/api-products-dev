package com.demo.apiproducts.dtos.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestHighProductImage implements Serializable{
   private String link;
   private String provider;
   private Boolean principal;
}

