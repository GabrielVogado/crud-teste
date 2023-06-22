package com.santander.document;

import com.santander.entity.Problem;
import com.santander.request.ProblemRequest;
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


public interface ProblemDocument {

    @Operation(summary = "Insere Dados de uma Problem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Problem Inserida com Sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao Inserir Objeto",
                    content = @Content) })
    ResponseEntity<ItemCreatedResponse> salvarProblem(@Parameter(description = "Dados pertinentes para o cadastro de Problem") @RequestBody ProblemRequest problemRequest);

    @Operation(summary = "Busca Problem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Problem Encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class)) }),
            @ApiResponse(responseCode = "204", description = "Problem não encontrada",
                    content = @Content) })
    ResponseEntity<Problem> getProblemById(Long id);

    @Operation(summary = "Busca uma Lista de Problem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista Encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class)) }),
            @ApiResponse(responseCode = "204", description = "Lista não encontrada",
                    content = @Content) })
    ResponseEntity<List<Problem>> listarProblem();


    @Operation(summary = "Excluir Dados de uma Problem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Problem Excluida com Sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Problem.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro ao Alterar Objeto",
                    content = @Content) })
    ResponseEntity excluirProblem(@Parameter(description = "Dados pertinentes para o Exclusão de Problem") @PathVariable Long id);
}
