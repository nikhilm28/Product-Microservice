package com.product.service.controller;


import com.product.service.dto.ProductDTO;
import com.product.service.dto.ProductsDTO;
import com.product.service.entity.ProductEntity;
import com.product.service.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private static final String PRODUCT_ADDED = "Product added";
    private static final String PRODUCT_OUT_OF_STOCK = "Product out of stock";

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductsDTO> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductEntity productEntity){
        productService.saveProduct(productEntity);
        return new ResponseEntity<>(PRODUCT_ADDED,HttpStatus.resolve(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id){
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO==null){
            return new ResponseEntity<>(PRODUCT_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReservedQuantity(@PathVariable String id,
                                                         @RequestBody ProductEntity product){
        return new ResponseEntity<>(productService.updateQuantity(id,product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
