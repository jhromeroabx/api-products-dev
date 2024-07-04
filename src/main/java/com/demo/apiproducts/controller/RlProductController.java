package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.request.RequestHighProduct;
import com.demo.apiproducts.dtos.response.ResponseHighProduct;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.service.RlProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RlProductController {

   private final RlProductService rlProductService;

   @GetMapping("/products/{idProduct}")
   public ResponseEntity <ResponseProductByIdDTO> getProductById(@AuthenticationPrincipal User user,
                                                                 @PathVariable Long idProduct) {
      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductDTOById(user.getUsername(), idProduct));
   }

   @PostMapping("/products")
   public ResponseEntity <ResponseHighProduct> createProduct(@RequestBody RequestHighProduct requestHighProduct) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.createProductDTO(requestHighProduct));
   }
}
