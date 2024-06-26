package com.demo.apiproducts.controller;

import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.service.RlProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RlProductController {

   private final RlProductService rlProductService;

   @GetMapping("/products/{idProduct}")
   public ResponseEntity <ResponseProductByIdDTO> getProductById(@PathVariable Long idProduct) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
         throw new IllegalStateException("Unexpected authentication type");
      }
      User user = (User) authentication.getPrincipal();
      String userId = user.getUsername();
      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductDTOById(idProduct, userId));
   }

}
