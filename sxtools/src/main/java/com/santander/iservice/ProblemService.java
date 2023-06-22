package com.santander.iservice;


import com.santander.request.ProblemRequest;
import com.santander.response.ItemCreatedResponse;
import com.santander.response.ProblemResponse;
import com.santander.entity.Problem;

import java.util.List;

public interface ProblemService {

    List<Problem> getProblem();

    Problem getProblemById(Long idProblem);

    ItemCreatedResponse cadastraProblem(ProblemRequest productRequest);

    ProblemResponse deletarProblem(Long idProblem);
}
