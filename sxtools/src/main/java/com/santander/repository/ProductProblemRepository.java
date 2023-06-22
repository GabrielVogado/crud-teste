package com.santander.repository;

import com.santander.entity.ProductProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductProblemRepository extends JpaRepository<ProductProblem, Long> {
    List<ProductProblem> findByProductId(Long productId);

    Optional<ProductProblem> findByProductIdAndProblemId(Long productId, Long problemId);


}
