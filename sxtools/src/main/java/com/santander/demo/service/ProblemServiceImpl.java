package com.santander.demo.service;

import com.santander.demo.request.ProblemRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.response.ProblemResponse;
import com.santander.demo.repository.ProblemRepository;
import com.santander.demo.entity.Problem;
import com.santander.demo.iservice.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public List<Problem> getProblem() {
        return problemRepository.findAll();
    }

    @Override
    public Problem getProblemById(Long idProblem) {
        return problemRepository.findById(idProblem).orElse(null);
    }

    @Override
    public ItemCreatedResponse cadastraProblem(ProblemRequest problemRequest) {
        Problem problem = Problem.builder()
                .active(Boolean.TRUE)
                .createDate(LocalDateTime.now())
                .name(problemRequest.getName())
                .build();
        Problem savedProblem = problemRepository.save(problem);
        return ItemCreatedResponse.builder()
                .createDate(savedProblem.getCreateDate())
                .id(savedProblem.getId())
                .build();
    }

    @Override
    public ProblemResponse deletarProblem(Long idProblem) {
        Problem problemUpdate = getProblemById(idProblem);
        problemUpdate.setActive(Boolean.FALSE);
        Problem savedProblem = problemRepository.save(problemUpdate);
        return ProblemResponse.builder().name(savedProblem.getName()).description(savedProblem.getDescription()).build();
    }
}
