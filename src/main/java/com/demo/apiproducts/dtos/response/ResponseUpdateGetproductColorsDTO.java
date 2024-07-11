package com.demo.apiproducts.dtos.response;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUpdateGetproductColorsDTO implements Serializable {
   private List <ResponseGetproductColorsDTO> colors;
}
