package com.santander.service;

import com.santander.request.ProductProblemRequest;
import com.santander.response.ProductProblemResponse;
import com.santander.repository.ProductProblemRepository;
import com.santander.entity.ProductProblem;
import com.santander.iservice.ProductProblemService;
import com.santander.iservice.ProblemService;
import com.santander.iservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductProblemServiceImpl implements ProductProblemService {

    private final ProductProblemRepository productProblemRepository;

    private final ProblemService problemService;

    private final ProductService productService;

    @Autowired
    public ProductProblemServiceImpl(ProductProblemRepository productProblemRepository, ProblemService problemService, ProductService productService) {
        this.productProblemRepository = productProblemRepository;
        this.problemService = problemService;
        this.productService = productService;
    }

    @Override
    //@Cacheable(value = "productproblemIdCache" , key = "#productId", unless="#result == null")
    public List<ProductProblem> getProblemProduct(Long productId) {
        return productProblemRepository.findByProductId(productId);
    }

    @Override
    public ProductProblemResponse cadastraProblemProduct(Long productId, ProductProblemRequest productProblemRequest) {
        ProductProblem associationProductProblem = ProductProblem.builder()
                .problem(problemService.getProblemById(productProblemRequest.getProblemId()))
                .product(productService.getProductById(productId))
                .isActive(Boolean.TRUE)
                .activationDate(LocalDateTime.now()).build();
        ProductProblem savedAssociation = productProblemRepository.save(associationProductProblem);
        return ProductProblemResponse.builder().createDate(savedAssociation.getActivationDate()).build();
    }

    @Override
    public void deletarProblemProduct(Long productId, Long problemId) {
        ProductProblem productProblem = productProblemRepository.findByProductIdAndProblemId(productId, problemId).orElseThrow();
        productProblemRepository.delete(productProblem);
    }

}
