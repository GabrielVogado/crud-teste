package com.santander.iservice;


import com.santander.request.ProductRequest;
import com.santander.response.ItemCreatedResponse;
import com.santander.response.ProductResponse;
import com.santander.entity.Product;

import java.util.List;
public interface ProductService {

    List<Product> getProduct();

    ItemCreatedResponse cadastraProduct(ProductRequest productRequest);

    Product getProductById(Long id);

    ProductResponse deletarProduto(Long id);
}
