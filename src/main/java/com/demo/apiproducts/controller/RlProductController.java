package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.service.RlProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RlProductController {

   private final RlProductService rlProductService;

   @GetMapping("/products/{idProduct}")
   public ResponseEntity <ResponseProductByIdDTO> getProductById(@AuthenticationPrincipal User user, @PathVariable Long idProduct) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductDTOById(user.getUsername(), idProduct));
   }

   @GetMapping
   public ProductResponse getProducts(
           @RequestParam(value = "idProductType", required = false) Integer idProductType,
           @RequestParam(value = "productName", required = false) String productName,
           @RequestParam(value = "onlyFavorite", defaultValue = "false") boolean onlyFavorite,
           @RequestParam(value = "page", defaultValue = "1") int page,
           @RequestParam(value = "size", defaultValue = "10") int size
   ){
      if (page < 1 || size < 1 || size > 50){
         throw  new IllegalArgumentException("Invalid page or size paramenter");
      }
      ProductResponse productPage = productService.getProducts(idProductType, productName, onlyFavorite, page, size);
      return productService.getProducts(idProductType, productName, onlyFavorite, page, size);
   }

}
