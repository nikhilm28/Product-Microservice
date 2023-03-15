package com.product.service.service;


import com.product.service.dto.ProductDTO;
import com.product.service.dto.ProductsDTO;
import com.product.service.dto.ReservedProductDTO;
import com.product.service.entity.ProductEntity;

public interface ProductService {

    ProductsDTO getAllProducts();

    ProductDTO saveProduct(ProductEntity product);

    ProductDTO getProductById(String id);

    ReservedProductDTO updateQuantity(String id, ProductEntity productEntity);

    void deleteProduct(String id);
}
