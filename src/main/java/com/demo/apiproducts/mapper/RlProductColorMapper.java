package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.request.RequestCreateProductColor;
import com.demo.apiproducts.dtos.response.ResponseGetproductColorsDTO;
import com.demo.apiproducts.exception.ColorNotFoundException;
import com.demo.apiproducts.model.RlProductColor;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RlProductColorMapper {

   @Mapping(source = "description", target = "description")
   RlProductColor toRlProductColor(RequestCreateProductColor requestCreateProductColor);

   default String toDescription(List <RlProductColor> productColors) {
      return productColors.stream()
                          .map(RlProductColor::getDescription)
                          .findFirst()
                          .orElseThrow(() -> ColorNotFoundException.builder()
                                                                   .message("Color not found")
                                                                   .build());

   }

   @Mapping(source = "id", target = "id")
   @Mapping(source = "description", target = "description")
   ResponseGetproductColorsDTO toColors(ResponseGetproductColorsDTO responseGetproductColorsDTO);

}
