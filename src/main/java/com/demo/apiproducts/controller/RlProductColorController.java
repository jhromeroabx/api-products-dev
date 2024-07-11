package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.response.ResponseGetproductColorsDTO;
import com.demo.apiproducts.model.RlProductColor;
import com.demo.apiproducts.service.RlColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class RlProductColorController {

   private final RlColorService rlColorService ;

   @GetMapping("/products/{idProduct}/colors")
   public ResponseEntity <ResponseGetproductColorsDTO> getColors(Long idProduct) {

      return ResponseEntity.status(HttpStatus.OK).body(rlColorService.getColors(idProduct));

   }
}
