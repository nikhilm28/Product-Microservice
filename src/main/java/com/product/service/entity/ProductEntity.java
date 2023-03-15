package com.product.service.entity;

import com.product.service.enums.CategoryEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class ProductEntity implements Serializable {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private BigDecimal price;

    private Integer inStock;

    private Integer reservedQuantity;

    private Boolean available;

}
