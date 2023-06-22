package com.santander.demo.iservice;


import com.santander.demo.request.ProductProblemRequest;
import com.santander.demo.response.ProductProblemResponse;
import com.santander.demo.entity.ProductProblem;

import java.util.List;

public interface ProductProblemService {

    List<ProductProblem> getProblemProduct(Long productId);

    ProductProblemResponse cadastraProblemProduct(Long productId, ProductProblemRequest productRequest);

    void deletarProblemProduct(Long productId, Long problemId);
}
