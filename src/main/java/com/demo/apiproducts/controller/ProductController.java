package com.demo.apiproducts.controller;

import com.demo.apiproducts.dto.ProductResponse;
import com.demo.apiproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
   @Autowired
   private ProductService productService;

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
