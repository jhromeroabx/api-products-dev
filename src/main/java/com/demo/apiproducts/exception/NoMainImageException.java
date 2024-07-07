package com.demo.apiproducts.exception;

import lombok.Builder;

public class NoMainImageException extends RuntimeException {

   @Builder
   public NoMainImageException(String message) {
      super(message);
   }

}
