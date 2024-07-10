package com.demo.apiproducts.dtos.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductTyDTO implements Serializable {

   private Integer idProductType;
   private String descripcion;

}
