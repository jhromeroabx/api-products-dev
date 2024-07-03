package com.demo.apiproducts.dtos.request;

import java.util.List;

public class RequestHighProduct {
   private String name;
   private int idType;
   private Character currency;
   private Double price;
   private String description;
   private List <RequestHighProductImage> productImages;
}
