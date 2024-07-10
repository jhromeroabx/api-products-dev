package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.response.ResponseProductTyDTO;
import com.demo.apiproducts.dtos.response.ResponseProductTypeDTO;
import com.demo.apiproducts.model.RlProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RlProductTypeMapper {

   @Mapping(source = "id", target = "idProductType")
   @Mapping(source = "description", target = "description")
   ResponseProductTypeDTO toResponseGetProductTypeDTO(RlProductType productTypeModel);

   @Mapping(source = "id", target = "idProductType")
   @Mapping(source = "description", target = "descripcion")
   ResponseProductTyDTO toResponseProductTyDTO(RlProductType productTypeModel);

}
