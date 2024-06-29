package com.demo.apiproducts.handler;

import com.demo.apiproducts.dtos.response.ErrorDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class IdNotFoundExceptionHandler {

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

}
