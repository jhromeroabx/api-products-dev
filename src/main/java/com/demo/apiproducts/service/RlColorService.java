package com.demo.apiproducts.service;

import com.demo.apiproducts.dtos.response.ResponseGetproductColorsDTO;
import com.demo.apiproducts.exception.IdNotFoundException;
import com.demo.apiproducts.mapper.RlProductColorMapper;
import com.demo.apiproducts.model.RlProductColor;
import com.demo.apiproducts.repository.ProductColorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RlColorService {
   private final ProductColorRepository productColorRepository;
   private final RlProductColorMapper rlProductColorMapper;

   public ResponseGetproductColorsDTO getColors(Long idProduct){
     RlProductColor rlProductColorModel = productColorRepository.findById(idProduct).orElseThrow(
             () -> IdNotFoundException.builder()
                                      .message("The product with the ID: " + idProduct + " does not exist.")
                                      .build());

      ResponseGetproductColorsDTO colorsDTO = ResponseGetproductColorsDTO.builder()
                                                                         .id(rlProductColorModel.getId())
                                                                         .description(rlProductColorModel.getDescription())
                                                                         .build();

      ResponseGetproductColorsDTO colorsMapper = rlProductColorMapper.toColors(colorsDTO);


      return  colorsMapper;
   }
}
