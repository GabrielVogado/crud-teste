package com.santander.document;

import com.santander.entity.Product;
import com.santander.request.ProductRequest;
import com.santander.response.ItemCreatedResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ProductDocument {

    @Operation(summary = "Busca uma Lista de Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista Encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "204", description = "Lista não encontrada",
                    content = @Content) })
    ResponseEntity<List<Product>> listarProduct();

    @Operation(summary = "Busca Product por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "204", description = "Product não encontrada",
                    content = @Content) })
    ResponseEntity<Product> buscaProduct(Long id);

    @Operation(summary = "Altera Dados de uma Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Alterada com Sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao Alterar Objeto",
                    content = @Content) })
    ResponseEntity alterarProduct(@Parameter(description = "Dados pertinentes para o Alteração de Products") @PathVariable Long id);


    @Operation(summary = "Salvar Dados de uma Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Excluida com Sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao Alterar Objeto",
                    content = @Content) })
    ResponseEntity<ItemCreatedResponse> insertProduct(@RequestBody ProductRequest productRequest);
}
