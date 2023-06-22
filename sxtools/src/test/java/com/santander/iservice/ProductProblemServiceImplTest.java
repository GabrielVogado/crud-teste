package com.santander.iservice;


import com.santander.request.ProductProblemRequest;
import com.santander.response.ProductProblemResponse;
import com.santander.repository.ProductProblemRepository;
import com.santander.entity.Problem;
import com.santander.entity.Product;
import com.santander.entity.ProductProblem;
import com.santander.service.ProductProblemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductProblemServiceImplTest {

    @Mock
    private ProductProblemRepository productProblemRepository;

    @Mock
    private ProblemService problemService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductProblemServiceImpl associationService;

    @Before
    public void setup() {
        // Configuração inicial do mock, se necessário
    }

    @Test
    public void testGetProblemProduct() {
        // Cenário
        Long productId = 1L;
        ProductProblem productProblem1 = new ProductProblem();
        ProductProblem productProblem2 = new ProductProblem();
        List<ProductProblem> productProblemList = Arrays.asList(productProblem1, productProblem2);

        when(productProblemRepository.findByProductId(productId)).thenReturn(productProblemList);

        // Execução
        List<ProductProblem> result = associationService.getProblemProduct(productId);

        // Verificação
        assertEquals(2, result.size());
        verify(productProblemRepository, times(1)).findByProductId(productId);
    }

    @Test
    public void testCadastraProblemProduct() {
        // Cenário
        Long productId = 1L;
        Long problemId = 1L;
        ProductProblemRequest productProblemRequest = ProductProblemRequest.builder().build();
        productProblemRequest.setProblemId(problemId);

        ProductProblem savedAssociation = new ProductProblem();
        savedAssociation.setActivationDate(LocalDateTime.now());

        when(problemService.getProblemById(problemId)).thenReturn(new Problem());
        when(productService.getProductById(productId)).thenReturn(new Product());
        when(productProblemRepository.save(any(ProductProblem.class))).thenReturn(savedAssociation);

        // Execução
        ProductProblemResponse result = associationService.cadastraProblemProduct(productId, productProblemRequest);

        // Verificação
        assertEquals(savedAssociation.getActivationDate(), result.getCreateDate());
        verify(problemService, times(1)).getProblemById(problemId);
        verify(productService, times(1)).getProductById(productId);
        verify(productProblemRepository, times(1)).save(any(ProductProblem.class));
    }

    @Test
    public void testDeletarProblemProduct() {
        // Cenário
        Long productId = 1L;
        Long problemId = 1L;
        ProductProblem productProblem = new ProductProblem();

        when(productProblemRepository.findByProductIdAndProblemId(productId, problemId)).thenReturn(Optional.of(productProblem));

        // Execução
        associationService.deletarProblemProduct(productId, problemId);

        // Verificação
        verify(productProblemRepository, times(1)).findByProductIdAndProblemId(productId, problemId);
        verify(productProblemRepository, times(1)).delete(productProblem);
    }
}