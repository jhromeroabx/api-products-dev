package com.demo.apiproducts.dtos.request;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDailyofferDTO implements Serializable {

   @NotNull
   private Long idProduct;

}
