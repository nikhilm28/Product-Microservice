package com.product.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    String id;

    String name;

    String category;

    BigDecimal price;

    @JsonProperty("in_stock")
    Integer stock;

    @JsonProperty("reserved_quantity")
    Integer reservedQuantity;

    @JsonProperty("is_available")
    Boolean available;

}
