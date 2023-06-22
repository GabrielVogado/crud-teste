package com.santander.demo.service;

import com.santander.demo.request.ProductProblemRequest;
import com.santander.demo.response.ProductProblemResponse;
import com.santander.demo.repository.ProductProblemRepository;
import com.santander.demo.entity.ProductProblem;
import com.santander.demo.iservice.ProductProblemService;
import com.santander.demo.iservice.ProblemService;
import com.santander.demo.iservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
