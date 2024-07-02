package com.demo.apiproducts.handler;

import com.demo.apiproducts.dtos.response.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ConstraintViolationExceptionErrorHandler {

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

}
