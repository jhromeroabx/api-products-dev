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
public class ResponseGetProductTypeDTO implements Serializable {

   private List <ResponseProductTypeDTO> productTypes;

}
