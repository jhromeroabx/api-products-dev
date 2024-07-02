package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.response.ResponseProductDTO;
import com.demo.apiproducts.model.RlProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RlProductTypeMapper.class, RlProductImageMapper.class})
public interface RlProductMapper {

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "productImages", target = "image")
   @Mapping(source = "description", target = "description")
   ResponseProductDTO toResponseProductDTO(RlProduct productModel);
}
