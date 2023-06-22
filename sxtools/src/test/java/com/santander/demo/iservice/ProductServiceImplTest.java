package com.santander.demo.iservice;

import com.santander.demo.request.ProductRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.response.ProductResponse;
import com.santander.demo.repository.ProductRepository;
import com.santander.demo.entity.Product;
import com.santander.demo.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setup() {
        // Configuração inicial do mock, se necessário
    }

    @Test
    public void testGetProduct() {
        // Cenário
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(productList);

        // Execução
        List<Product> result = productService.getProduct();

        // Verificação
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testCadastraProduct() {
        // Cenário
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Test Product");
        productRequest.setActive(true);
        productRequest.setDescription("Test Description");
        productRequest.setProductFatherId(1L);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName(productRequest.getName());
        savedProduct.setActive(productRequest.getActive());
        savedProduct.setDescription(productRequest.getDescription());
        savedProduct.setProductFatherId(productRequest.getProductFatherId());
        savedProduct.setCreateDate(LocalDateTime.now());

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Execução
        ItemCreatedResponse result = productService.cadastraProduct(productRequest);

        // Verificação
        assertEquals(savedProduct.getCreateDate(), result.getCreateDate());
        assertEquals(savedProduct.getId(), result.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {
        // Cenário
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.getReferenceById(productId)).thenReturn(product);

        // Execução
        Product result = productService.getProductById(productId);

        // Verificação
        assertEquals(productId, result.getId());
        verify(productRepository, times(1)).getReferenceById(productId);
    }

    @Test
    public void testDeletarProduto() {
        // Cenário
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setActive(true);
        product.setDescription("Test Description");
        product.setProductFatherId(1L);

        when(productRepository.getReferenceById(productId)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Execução
        ProductResponse result = productService.deletarProduto(productId);

        // Verificação
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(false, result.getActive());
        assertEquals(product.getProductFatherId(), result.getProductFatherId());
        verify(productRepository, times(1)).getReferenceById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
