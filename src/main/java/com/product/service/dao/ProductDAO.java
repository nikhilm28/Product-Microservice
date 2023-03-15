package com.product.service.dao;


import com.product.service.entity.ProductEntity;

public interface ProductDAO {

    ProductEntity getProductById(String id);

    void deleteProduct(String id);

    ProductEntity updateProduct(String productId, ProductEntity product);

}
