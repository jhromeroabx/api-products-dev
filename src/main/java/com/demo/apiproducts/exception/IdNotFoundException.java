package com.demo.apiproducts.exception;

import lombok.Builder;

public class IdNotFoundException extends RuntimeException {

   @Builder
   public IdNotFoundException(String message) {
      super(message);
   }

}
