package com.demo.apiproducts.exception;

import lombok.Builder;

public class MainImageNotFoundException extends RuntimeException {

   @Builder
   public MainImageNotFoundException(String message) {
      super(message);
   }

}
