package com.demo.apiproducts.handler;

import com.demo.apiproducts.dtos.response.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class IllegalArgumentExceptionErrorHandler {

   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity <ErrorDTO> IllegalArgumentExceptionHandler(IllegalArgumentException e) {
      log.error("Illegal Argument", e);
      ErrorDTO error = ErrorDTO
              .builder()
              .message(e.getMessage())
              .code("ILLEGAL_ARGUMENT")
              .status(HttpStatus.BAD_REQUEST.value())
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }
}
