package com.santander.demo.iservice;


import com.santander.demo.request.ProductRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.response.ProductResponse;
import com.santander.demo.entity.Product;

import java.util.List;
public interface ProductService {

    List<Product> getProduct();

    ItemCreatedResponse cadastraProduct(ProductRequest productRequest);

    Product getProductById(Long id);

    ProductResponse deletarProduto(Long id);
}
