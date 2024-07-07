package com.demo.apiproducts.exception;

import lombok.Builder;

public class MultipleMainImagesException extends RuntimeException {

   @Builder
   public MultipleMainImagesException(String message) {
      super(message);
   }

}
