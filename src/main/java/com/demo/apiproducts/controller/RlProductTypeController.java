package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.response.ResponseGetProductTypeDTO;
import com.demo.apiproducts.service.RlProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RlProductTypeController {

   private final RlProductTypeService rlProductTypeService;

   @GetMapping("/product-types")
   public ResponseEntity <ResponseGetProductTypeDTO> getAllProductTypes() {
      return ResponseEntity.status(HttpStatus.OK).body(rlProductTypeService.getAllProductTypesDTO());
   }
}
