package com.santander.demo.controller;


import com.santander.demo.request.ProductProblemRequest;
import com.santander.demo.response.ProductProblemResponse;
import com.santander.demo.entity.ProductProblem;
import com.santander.demo.iservice.ProductProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products/{product_Id}/problems")
public class ProductProblemController {

    private final ProductProblemService productProblemService;

    @Autowired
    public ProductProblemController(ProductProblemService productProblemService) {
        this.productProblemService = productProblemService;
    }

    @GetMapping
    public ResponseEntity<List<ProductProblem>> listProblemsByProduct(@PathVariable(value = "product_Id") Long productId) {
        return new ResponseEntity<>(productProblemService.getProblemProduct(productId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductProblemResponse> createNewAssociation(@PathVariable(value = "product_Id") Long productId, @RequestBody ProductProblemRequest productProblemRequest) {
        return new ResponseEntity<>(productProblemService.cadastraProblemProduct(productId, productProblemRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{problemId}")
    public ResponseEntity deletarProblem(@PathVariable(value = "product_Id") Long productId, @PathVariable(value = "problemId") Long problemId) {
        productProblemService.deletarProblemProduct(productId, problemId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
