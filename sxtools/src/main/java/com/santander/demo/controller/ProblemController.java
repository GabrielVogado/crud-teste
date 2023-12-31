package com.santander.demo.controller;


import com.santander.demo.document.ProblemDocument;
import com.santander.demo.request.ProblemRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.entity.Problem;
import com.santander.demo.iservice.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("problem")
public class ProblemController implements ProblemDocument {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    public ResponseEntity<ItemCreatedResponse> salvarProblem(@RequestBody ProblemRequest problemRequest) {
        return new ResponseEntity<>(problemService.cadastraProblem(problemRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{problem_Id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable(value = "problem_Id") Long idProblem) {
        return new ResponseEntity<>(
                problemService.getProblemById(idProblem), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Problem>> listarProblem() {
        return new ResponseEntity<>( problemService.getProblem(), HttpStatus.OK);
    }

    @PutMapping("/{problemId}")
    public ResponseEntity excluirProblem(@PathVariable(value = "problemId") Long idProblem) {
        problemService.deletarProblem(idProblem);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
