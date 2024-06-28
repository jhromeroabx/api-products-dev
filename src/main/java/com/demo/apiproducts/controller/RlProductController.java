package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.dtos.response.request.RequestProductDailyofferDTO;
import com.demo.apiproducts.service.RlProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RlProductController {

   private final RlProductService rlProductService;

   @GetMapping("/products/{idProduct}")
   public ResponseEntity <ResponseProductByIdDTO> getProductById(@AuthenticationPrincipal User user, @PathVariable Long idProduct) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductDTOById(user.getUsername(), idProduct));
   }

   @PutMapping("/products/daily-offer")
   public ResponseEntity <ResponseProductByIdDTO>  putDailyOffer(RequestProductDailyofferDTO params) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.putDailyOffer( params.getIdProduct()));
   }

}
