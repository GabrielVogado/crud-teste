package com.santander.iservice;


import com.santander.request.ProductProblemRequest;
import com.santander.response.ProductProblemResponse;
import com.santander.entity.ProductProblem;

import java.util.List;

public interface ProductProblemService {

    List<ProductProblem> getProblemProduct(Long productId);

    ProductProblemResponse cadastraProblemProduct(Long productId, ProductProblemRequest productRequest);

    void deletarProblemProduct(Long productId, Long problemId);
}
