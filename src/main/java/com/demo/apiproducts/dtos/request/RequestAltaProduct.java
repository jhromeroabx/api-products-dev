package com.demo.apiproducts.dtos.request;

import com.demo.apiproducts.model.RlProductImage;
import java.util.List;

public class RequestAltaProduct {
   private String name;
   private int idType;
   private Character currency;
   private Double price;
   private String description;
   private List <RlProductImage> productImages;

}
