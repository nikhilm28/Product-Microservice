package com.product.service.dao.impl;

import com.product.service.dao.ProductDAO;
import com.product.service.entity.ProductEntity;
import com.product.service.exceptions.ProductNotFoundException;
import com.product.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDAOImpl implements ProductDAO {


    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable(key = "#id",value = "ProductEntity")
    public ProductEntity getProductById(String id) {
        return productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product not Found..!!"));
    }

    @Override
    @CacheEvict(key = "#id",value = "ProductEntity",allEntries = false)
    public void deleteProduct(String id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        ProductEntity entity = product.get();
        productRepository.delete(entity);
    }

    @Override
    @CachePut(key = "#id",value = "ProductEntity")
    public ProductEntity updateProduct(String id, ProductEntity newProduct) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        ProductEntity oldProduct = productEntity.get();
        int inStock = oldProduct.getInStock();
        int updateReservedQuantity = oldProduct.getReservedQuantity()
                + newProduct.getReservedQuantity();
        if (inStock > newProduct.getReservedQuantity()){
            oldProduct.setReservedQuantity(updateReservedQuantity);
            oldProduct.setInStock(inStock - newProduct.getReservedQuantity());
        }
        productRepository.save(oldProduct);
        return oldProduct;
    }
}
