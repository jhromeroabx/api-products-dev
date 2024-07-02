package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.response.ResponseProductImageDTO;
import com.demo.apiproducts.model.RlProductImage;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RlProductImageMapper {

   @Mapping(source = "providerLink", target = "link")
   ResponseProductImageDTO toDTO(RlProductImage rlProductImage);

   default String toLinks(List <RlProductImage> productImages) {
      return productImages.stream()
                          .map(RlProductImage::getProviderLink)
                          .collect(Collectors.joining(", "));
   }
}
