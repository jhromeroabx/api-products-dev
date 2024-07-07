package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.request.RequestCreateProductImage;
import com.demo.apiproducts.dtos.response.ResponseProductImageDTO;
import com.demo.apiproducts.model.RlProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RlProductMapper.class})
public interface RlProductImageMapper {

   @Mapping(source = "providerLink", target = "link")
   ResponseProductImageDTO toDTO(RlProductImage rlProductImage);


   @Mapping(source = "link", target = "providerLink")
   @Mapping(source = "provider", target = "provider")
   @Mapping(source = "principal", target = "principal")
   RlProductImage toModel(RequestCreateProductImage requestHighProductImage);
}
