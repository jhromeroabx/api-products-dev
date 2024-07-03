package com.demo.apiproducts.dtos.response.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDailyofferDTO implements Serializable {
    @NotNull
    private Long idProduct;

}
