package com.demo.apiproducts.handler;

import com.demo.apiproducts.dtos.response.ErrorDTO;
import com.demo.apiproducts.exception.RepeatedProductInFavoritesListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RepeatedProductInFavoritesListExceptionErrorHandler {

   @ExceptionHandler(RepeatedProductInFavoritesListException.class)
   public ResponseEntity <ErrorDTO> handleException(RepeatedProductInFavoritesListException e) {
      log.error("Repeated product in the favorites list.", e);

      ErrorDTO error = ErrorDTO
              .builder()
              .message(e.getMessage())
              .code("REPEATED_PRODUCT_IN_FAVORITES_LIST")
              .status(HttpStatus.BAD_REQUEST.value())
              .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

   }

}
