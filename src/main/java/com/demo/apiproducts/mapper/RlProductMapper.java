package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.request.RequestCreateProduct;
import com.demo.apiproducts.dtos.response.ResponseCreateProduct;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.model.RlProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RlProductTypeMapper.class, RlProductImageMapper.class})
public interface RlProductMapper {

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "productImages", target = "images")
   @Mapping(target = "isFavorite", ignore = true)
   ResponseProductByIdDTO toResponseProductByIdDTO(RlProduct product);

   @Mapping(source = "name", target = "name")
   @Mapping(source = "idType", target = "productType.id")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "description", target = "description")
   @Mapping(source = "largeDescription", target = "largeDescription")
   @Mapping(source = "images", target = "productImages")
   RlProduct toModel(RequestCreateProduct requestCreateProduct);

   @Mapping(source = "name", target = "name")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "description", target = "description")
   @Mapping(source = "largeDescription", target = "largeDescription")
   @Mapping(source = "productImages", target = "images")
   ResponseCreateProduct toResponseCreateProduct(RlProduct product);

}

