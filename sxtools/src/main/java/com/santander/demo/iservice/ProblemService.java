package com.santander.demo.iservice;


import com.santander.demo.request.ProblemRequest;
import com.santander.demo.response.ItemCreatedResponse;
import com.santander.demo.response.ProblemResponse;
import com.santander.demo.entity.Problem;

import java.util.List;

public interface ProblemService {

    List<Problem> getProblem();

    Problem getProblemById(Long idProblem);

    ItemCreatedResponse cadastraProblem(ProblemRequest productRequest);

    ProblemResponse deletarProblem(Long idProblem);
}
