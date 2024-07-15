package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.request.RequestCreateProductImage;
import com.demo.apiproducts.dtos.response.ResponseProductImageDTO;
import com.demo.apiproducts.exception.MainImageNotFoundException;
import com.demo.apiproducts.model.RlProductImage;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RlProductImageMapper {

   @Mapping(source = "providerLink", target = "link")
   ResponseProductImageDTO toDTO(RlProductImage rlProductImage);


   @Mapping(source = "link", target = "providerLink")
   @Mapping(source = "provider", target = "provider")
   @Mapping(source = "principal", target = "principal")
   RlProductImage toModel(RequestCreateProductImage requestHighProductImage);

   @Named("toLinks")
   default String toLinks(List <RlProductImage> productImages) {
      return productImages.stream()
                          .filter(RlProductImage::getPrincipal)
                          .map(RlProductImage::getProviderLink)
                          .findFirst()
                          .orElseThrow(() -> MainImageNotFoundException.builder()
                                                                       .message("Main image not found")
                                                                       .build());
   }
}
