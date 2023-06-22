package com.santander.demo.controller;


import com.santander.demo.document.ProductDocument;
import com.santander.demo.request.ProductRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.entity.Product;
import com.santander.demo.iservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController implements ProductDocument {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ItemCreatedResponse> insertProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.cadastraProduct(productRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Product>> listarProduct() {
        return new ResponseEntity<>( productService.getProduct(), HttpStatus.OK);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> buscaProduct(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>( productService.getProductById(productId), HttpStatus.OK);
    }



    @PutMapping("/{productId}")
        public ResponseEntity alterarProduct(@PathVariable(value = "productId") Long productId) {
        productService.deletarProduto(productId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
