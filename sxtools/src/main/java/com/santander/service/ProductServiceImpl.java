package com.santander.service;


import com.santander.request.ProductRequest;
import com.santander.response.ItemCreatedResponse;
import com.santander.response.ProductResponse;
import com.santander.repository.ProductRepository;
import com.santander.entity.Product;
import com.santander.iservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public ItemCreatedResponse cadastraProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .active(productRequest.getActive())
                .createDate(LocalDateTime.now())
                .description(productRequest.getDescription())
                .productFatherId(productRequest.getProductFatherId())
                .name(productRequest.getName())
                .build();

        Product insertProduct = productRepository.save(product);
        return ItemCreatedResponse.builder()
                .createDate(insertProduct.getCreateDate())
                .id(insertProduct.getId())
                .build();
    }

    @Override
    @Cacheable(value = "productIdCache" , key = "#id", unless="#result == null")
    public Product getProductById(Long id){
        return productRepository.getReferenceById(id);
    }

    @Override
    public ProductResponse deletarProduto(Long id) {
        Product product = productRepository.getReferenceById(id);
        product.setActive(false);
        Product savedProductDeleted = productRepository.save(product);
        return ProductResponse.builder()
                .name(savedProductDeleted.getName())
                .description(savedProductDeleted.getDescription())
                .active(savedProductDeleted.getActive())
                .productFatherId(savedProductDeleted.getProductFatherId())
                .build();
    }
}
