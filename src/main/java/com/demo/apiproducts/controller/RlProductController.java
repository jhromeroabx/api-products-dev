package com.demo.apiproducts.controller;


import com.demo.apiproducts.dtos.request.RequestCreateProduct;
import com.demo.apiproducts.dtos.request.RequestProductDailyofferDTO;
import com.demo.apiproducts.dtos.response.ResponseCreateProduct;
import com.demo.apiproducts.dtos.response.ResponseGetAllProductsDTO;
import com.demo.apiproducts.dtos.response.ResponseGetOfferOrProductDTO;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.dtos.response.ResponseProductFavDTO;
import com.demo.apiproducts.dtos.response.ResponseSimilarProductsDTO;
import com.demo.apiproducts.dtos.response.ResponseUpdateGetproductColorsDTO;
import com.demo.apiproducts.service.RlProductService;
import com.demo.apiproducts.service.UserFavoriteProductService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class RlProductController {

   private final RlProductService rlProductService;
   private final UserFavoriteProductService userFavoriteProductService;

   @GetMapping("/products/{idProduct}")
   public ResponseEntity <ResponseProductByIdDTO> getProductById(@AuthenticationPrincipal User user,
                                                                 @PathVariable Long idProduct) {
      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductDTOById(user.getUsername(), idProduct));
   }

   @PutMapping("/products/daily-offer")
   public ResponseEntity <ResponseProductByIdDTO> putDailyOffer(@AuthenticationPrincipal User user, @Valid @RequestBody RequestProductDailyofferDTO requestProductDailyofferDTO) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.putDailyOffer(user.getUsername(), requestProductDailyofferDTO.getIdProduct()));

   }

   @GetMapping("/products/daily-offer")
   public ResponseEntity <ResponseGetOfferOrProductDTO> getDailyOffer(@AuthenticationPrincipal User user) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getDailyOfferOrLastUserProduct(user.getUsername()));

   }

   @PostMapping("/products")
   public ResponseEntity <ResponseCreateProduct> createProduct(@RequestBody RequestCreateProduct requestHighProduct) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.createProductDTO(requestHighProduct));
   }

   @PutMapping("/products/{idProduct}/favorite")
   public ResponseEntity <ResponseProductFavDTO> addAndRemoveFavoriteProduct(@Valid @PathVariable Long idProduct,
                                                                             @AuthenticationPrincipal User user) {
      return ResponseEntity.status(HttpStatus.OK).body(userFavoriteProductService.addAndRemoveFavoriteProduct(idProduct, user.getUsername()));
   }

   @GetMapping("/products")
   public ResponseEntity <ResponseGetAllProductsDTO> getAllProducts(@Valid @RequestParam(value = "idProductType", required = false) Long idProductType,
                                                                    @Valid @RequestParam(value = "productName", required = false) String productName,
                                                                    @Valid @RequestParam(value = "onlyFavorite", defaultValue = "false") boolean onlyFavorite,
                                                                    @Valid @RequestParam(value = "page", defaultValue = "1") @Min(value = 1) int page,
                                                                    @Valid @RequestParam(value = "size", defaultValue = "10") @Min(value = 1) @Max(value = 50) int size,
                                                                    @AuthenticationPrincipal User user) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getAllProductsDTO(idProductType, productName, onlyFavorite, page, size, user.getUsername()));
   }

   @GetMapping("/products/{idProduct}/colors")
   public ResponseEntity <ResponseUpdateGetproductColorsDTO> getColors(@PathVariable Long idProduct) {
      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getProductColors(idProduct));
   }

   @GetMapping("/products/{idProduct}/similar")
   public ResponseEntity <ResponseSimilarProductsDTO> getAllSimilarProducts(@Valid @PathVariable Long idProduct,
                                                                            @Valid @RequestParam(value = "page", defaultValue = "1") @Min(value = 1) int page,
                                                                            @Valid @RequestParam(value = "size", defaultValue = "10") @Min(value = 1) @Max(value = 50) int size,
                                                                            @AuthenticationPrincipal User user) {

      return ResponseEntity.status(HttpStatus.OK).body(rlProductService.getSimilarProducts(idProduct, page, size, user.getUsername()));
   }
}
