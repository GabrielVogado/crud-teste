package com.santander.demo.service;


import com.santander.demo.request.ProductRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.response.ProductResponse;
import com.santander.demo.repository.ProductRepository;
import com.santander.demo.entity.Product;
import com.santander.demo.iservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
