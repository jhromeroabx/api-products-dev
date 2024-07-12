package com.demo.apiproducts.dtos.response;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseGetproductColorsDTO implements Serializable {

   private Long id;
   private String description;

}




