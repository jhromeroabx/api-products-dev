package com.demo.apiproducts.mapper;

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

}

