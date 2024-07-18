package com.demo.apiproducts.mapper;

import com.demo.apiproducts.dtos.request.RequestCreateProduct;
import com.demo.apiproducts.dtos.response.RSimilarProductDTO;
import com.demo.apiproducts.dtos.response.ResponseCreateProduct;
import com.demo.apiproducts.dtos.response.ResponseGetOfferOrProductDTO;
import com.demo.apiproducts.dtos.response.ResponseProductByIdDTO;
import com.demo.apiproducts.dtos.response.ResponseProductDTO;
import com.demo.apiproducts.model.RlProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RlProductTypeMapper.class, RlProductImageMapper.class, RlProductColorMapper.class})
public interface RlProductMapper {

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "productImages", target = "images")
   @Mapping(target = "isFavorite", ignore = true)
   ResponseProductByIdDTO toResponseProductByIdDTO(RlProduct product);

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "productImages", target = "images")
   @Mapping(source = "dailyOffer", target = "isDailyOffer")
   ResponseGetOfferOrProductDTO toResponseGetOfferOrProductDTO(RlProduct product);

   @Mapping(source = "name", target = "name")
   @Mapping(source = "idProductType", target = "productType.id")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "description", target = "description")
   @Mapping(source = "largeDescription", target = "largeDescription")
   @Mapping(source = "images", target = "productImages")
   @Mapping(source = "colors", target = "productColors")
   RlProduct toModel(RequestCreateProduct requestCreateProduct);

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "productImages", target = "images")
   @Mapping(source = "productColors", target = "colors")
   @Mapping(source = "description", target = "description")
   @Mapping(source = "largeDescription", target = "largeDescription")
   ResponseCreateProduct toResponseCreateProduct(RlProduct product);

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "productImages", target = "image", qualifiedByName = "toLinks")
   @Mapping(source = "description", target = "description")
   ResponseProductDTO toResponseProductDTO(RlProduct productModel);

   @Mapping(source = "id", target = "idProduct")
   @Mapping(source = "name", target = "name")
   @Mapping(source = "productType", target = "productType")
   @Mapping(source = "currency", target = "currency")
   @Mapping(source = "price", target = "price")
   @Mapping(source = "productImages", target = "image", qualifiedByName = "toLinks")
   @Mapping(source = "description", target = "description")
   RSimilarProductDTO toRSimilarProductDTO(RlProduct productModel);
}

