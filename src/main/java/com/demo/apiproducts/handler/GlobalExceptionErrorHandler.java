package com.demo.apiproducts.handler;

import com.demo.apiproducts.dtos.response.ErrorDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.exception.MainImageNotFoundException;
import com.demo.apiproducts.exception.MultipleMainImagesException;
import com.demo.apiproducts.exception.NoMainImageException;
import com.demo.apiproducts.exception.RepeatedProductInFavoritesListException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionErrorHandler {

   @ExceptionHandler(MultipleMainImagesException.class)
   public ResponseEntity <ErrorDTO> MultipleMainImagesExceptionHandler(MultipleMainImagesException e) {
      log.error("Multiple Main Images", e);
      ErrorDTO error = ErrorDTO
              .builder()
              .message(e.getMessage())
              .code("MULTIPLE_MAIN_IMAGES")
              .status(HttpStatus.BAD_REQUEST.value())
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

   @ExceptionHandler(NoMainImageException.class)
   public ResponseEntity <ErrorDTO> NoMainImageExceptionHandler(NoMainImageException e) {
      log.error("No Main Image", e);
      ErrorDTO error = ErrorDTO
              .builder()
              .message(e.getMessage())
              .code("NO_MAIN_IMAGE")
              .status(HttpStatus.BAD_REQUEST.value())
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

   @ExceptionHandler(IdNotFoundException.class)
   public ResponseEntity <ErrorDTO> IdNotFoundExceptionHandler(IdNotFoundException e) {
      log.error("Id Not Found", e);
      ErrorDTO errorDTO = ErrorDTO
              .builder()
              .message("Id Not Found")
              .code("ID_NOT_FOUND")
              .status(HttpStatus.NOT_FOUND.value())
              .build();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
   }

   @ExceptionHandler(RepeatedProductInFavoritesListException.class)
   public ResponseEntity <ErrorDTO> repeatedHandleException(RepeatedProductInFavoritesListException e) {
      log.error("Repeated product in the favorites list.", e);

      ErrorDTO error = ErrorDTO
              .builder()
              .message(e.getMessage())
              .code("REPEATED_PRODUCT_IN_FAVORITES_LIST")
              .status(HttpStatus.BAD_REQUEST.value())
              .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

   }

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity <ErrorDTO> validationErrorHandler(ConstraintViolationException e) {

      log.error("Validation error", e);
      ErrorDTO error = ErrorDTO.builder()
                               .message("Validation error")
                               .code("VALIDATION_ERROR")
                               .status(HttpStatus.BAD_REQUEST.value())
                               .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

   @ExceptionHandler(MainImageNotFoundException.class)
   public ResponseEntity <ErrorDTO> MainImageNotFoundExceptionHandler(MainImageNotFoundException e) {
      log.error("Main image in DB not found", e);
      ErrorDTO errorDTO = ErrorDTO
              .builder()
              .message("Main Image Not Found")
              .code("MAIN_IMAGE_NOT_FOUND")
              .status(HttpStatus.NOT_FOUND.value())
              .build();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
   }
}
