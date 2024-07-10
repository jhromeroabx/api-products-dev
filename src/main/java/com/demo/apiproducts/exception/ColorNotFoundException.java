package com.demo.apiproducts.exception;

import lombok.Builder;

public class ColorNotFoundException extends RuntimeException {

   @Builder
   public ColorNotFoundException(String message) {
      super(message);
   }
}
