package com.product.service.utility;


import com.product.service.dto.ProductDTO;
import com.product.service.dto.ReservedProductDTO;
import com.product.service.entity.ProductEntity;

public class MapperUtils {

    public static ProductDTO toProductDTO(ProductEntity productEntity){
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory().toString())
                .price(productEntity.getPrice())
                .stock(productEntity.getInStock())
                .reservedQuantity(productEntity.getReservedQuantity())
                .available(productEntity.getAvailable())
                .build();
    }

    public static ReservedProductDTO reservedProductDTO(ProductEntity product){
        return ReservedProductDTO.builder()
                .name(product.getName())
                .stock(product.getInStock())
                .reservedQuantity(product.getReservedQuantity())
                .available(product.getAvailable())
                .build();
    }

    public MapperUtils() {}
}
