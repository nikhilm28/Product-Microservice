package com.product.service.service.impl;

import com.product.service.dao.impl.ProductDAOImpl;
import com.product.service.dto.ProductDTO;
import com.product.service.dto.ProductsDTO;
import com.product.service.dto.ReservedProductDTO;
import com.product.service.entity.ProductEntity;

import com.product.service.repository.ProductRepository;
import com.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.product.service.utility.MapperUtils.reservedProductDTO;
import static com.product.service.utility.MapperUtils.toProductDTO;
import static com.product.service.utility.NotificationSenderUtils.generateNotificationBody;
import static com.product.service.utility.RandomGeneratorUtils.generateRandomUUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private ProductRepository productRepository;

    private ProductDAOImpl productDAO;

    public ProductServiceImpl(ProductRepository productRepository, ProductDAOImpl productDAO) {
        this.productRepository = productRepository;
        this.productDAO = productDAO;
    }

    @Override
    public ProductsDTO getAllProducts() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity productEntity:list){
            ProductDTO productDTO = toProductDTO(productEntity);
            dtoList.add(productDTO);
        }
        return ProductsDTO.builder()
                .products(dtoList)
                .build();
    }

    @Override
    public ProductDTO saveProduct(ProductEntity product){
        product.setId(generateRandomUUID());
        product.setReservedQuantity(0);
        ProductDTO productDTO = toProductDTO(productRepository.save(product));
        kafkaTemplate.send("Product_Notification",generateNotificationBody(productDTO));
        return productDTO;
    }

    @Override
    public ProductDTO getProductById(String id) {

        ProductEntity product = productDAO.getProductById(id);
        ProductDTO productDTO = toProductDTO(product);
        return productDTO;
    }

    @Override
    public ReservedProductDTO updateQuantity(String id, ProductEntity entity) {
        ProductEntity productEntity = productDAO.updateProduct(id,entity);
        ReservedProductDTO reservedProductDTO = reservedProductDTO(productEntity);
        return reservedProductDTO;
    }

    @Override
    public void deleteProduct(String id) {
        productDAO.deleteProduct(id);
    }
}
