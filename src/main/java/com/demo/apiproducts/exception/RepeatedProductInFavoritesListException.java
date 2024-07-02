package com.demo.apiproducts.exception;

import lombok.Builder;

public class RepeatedProductInFavoritesListException extends RuntimeException {

   @Builder
   public RepeatedProductInFavoritesListException(String message) {
      super(message);
   }

}
