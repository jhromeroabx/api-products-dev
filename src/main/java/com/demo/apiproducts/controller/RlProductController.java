package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.response.ResponseGetAllProductsDTO;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.service.RlProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class RlProductController {

   private final RlProductService rlProductService;

   @GetMapping("/products/{idProduct}")
   public ResponseEntity <ResponseProductByIdDTO> getProductById(@AuthenticationPrincipal User user, @PathVariable Long idProduct) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductDTOById(user.getUsername(), idProduct));
   }

   @GetMapping("/products")
   public ResponseEntity <ResponseGetAllProductsDTO> getAllProducts(@Valid @RequestParam(value = "idProductType", required = false) Integer idProductType,
                                                                    @Valid @RequestParam(value = "productName", required = false) String productName,
                                                                    @Valid @RequestParam(value = "onlyFavorite", defaultValue = "false") boolean onlyFavorite,
                                                                    @Valid @RequestParam(value = "page", defaultValue = "1") @Min(value = 1) int page,
                                                                    @Valid @RequestParam(value = "size", defaultValue = "10") @Min(value = 1) @Max(value = 50) int size,
                                                                    @AuthenticationPrincipal User user) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getAllProductsDTO(idProductType, productName, onlyFavorite, page, size, user.getUsername()));
   }

}
