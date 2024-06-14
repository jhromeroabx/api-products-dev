package com.demo.apiproducts.service;

import com.demo.apiproducts.dtos.response.ResponseGetProductTypeDTO;
import com.demo.apiproducts.dtos.response.ResponseProductTypeDTO;
import com.demo.apiproducts.mapper.RlProductTypeMapper;
import com.demo.apiproducts.model.RlProductType;
import com.demo.apiproducts.repository.ProductTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RlProductTypeService {

   private final ProductTypeRepository productTypeRepository;
   private final RlProductTypeMapper productTypeMapper;

   public ResponseGetProductTypeDTO getAllProductTypesDTO() {
      List <RlProductType> productTypeModel = productTypeRepository.findByDeletedAtIsNull();
      List <ResponseProductTypeDTO> responseGetProductTypeDTOList = productTypeModel.stream()
                                                                                    .map(productTypeMapper::toResponseGetProductTypeDTO)
                                                                                    .toList();
      return ResponseGetProductTypeDTO.builder()
                                      .productTypes(responseGetProductTypeDTOList)
                                      .build();
   }
}
