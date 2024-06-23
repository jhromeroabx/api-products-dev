package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.response.ResponseProductImageDTO;
import com.demo.apiproducts.model.RlProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RlProductImageMapper {

   @Mapping(source = "providerLink", target = "link")
   ResponseProductImageDTO toDTO(RlProductImage rlProductImage);
}
