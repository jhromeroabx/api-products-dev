package com.demo.apiproducts.dtos.response.request;

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

    private Long idProduct;

}
