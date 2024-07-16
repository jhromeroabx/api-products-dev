package com.demo.apiproducts.dtos.response;

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
public class ResponseSimilarProductsDTO implements Serializable {

   private Integer page;
   private Integer size;
   private Integer totalPages;
   private Long totalProducts;
   private List <RSimilarProductDTO> products;
}
