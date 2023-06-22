package com.santander.iservice;

import com.santander.request.ProblemRequest;
import com.santander.response.ItemCreatedResponse;
import com.santander.response.ProblemResponse;
import com.santander.repository.ProblemRepository;
import com.santander.entity.Problem;
import com.santander.service.ProblemServiceImpl;
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
public class ProblemServiceImplTest {

    @Mock
    private ProblemRepository problemRepository;

    @InjectMocks
    private ProblemServiceImpl problemService;

    @Before
    public void setup() {
        // Configuração inicial do mock, se necessário
    }

    @Test
    public void testGetProblem() {
        // Cenário
        Problem problem1 = new Problem();
        Problem problem2 = new Problem();
        List<Problem> problemList = Arrays.asList(problem1, problem2);

        when(problemRepository.findAll()).thenReturn(problemList);

        // Execução
        List<Problem> result = problemService.getProblem();

        // Verificação
        assertEquals(2, result.size());
        verify(problemRepository, times(1)).findAll();
    }

    @Test
    public void testGetProblemById() {
        // Cenário
        Long problemId = 1L;
        Problem problem = new Problem();
        problem.setId(problemId);

        when(problemRepository.findById(problemId)).thenReturn(Optional.of(problem));

        // Execução
        Problem result = problemService.getProblemById(problemId);

        // Verificação
        assertEquals(problemId, result.getId());
        verify(problemRepository, times(1)).findById(problemId);
    }

    @Test
    public void testCadastraProblem() {
        // Cenário
        ProblemRequest problemRequest = new ProblemRequest();
        problemRequest.setName("Test Problem");

        Problem savedProblem = new Problem();
        savedProblem.setId(1L);
        savedProblem.setActive(true);
        savedProblem.setCreateDate(LocalDateTime.now());
        savedProblem.setName(problemRequest.getName());

        when(problemRepository.save(any(Problem.class))).thenReturn(savedProblem);

        // Execução
        ItemCreatedResponse result = problemService.cadastraProblem(problemRequest);

        // Verificação
        assertEquals(savedProblem.getCreateDate(), result.getCreateDate());
        assertEquals(savedProblem.getId(), result.getId());
        verify(problemRepository, times(1)).save(any(Problem.class));
    }

    @Test
    public void testDeletarProblem() {
        // Cenário
        Long problemId = 1L;
        Problem problem = new Problem();
        problem.setId(problemId);
        problem.setName("Test Problem");
        problem.setActive(true);

        when(problemRepository.findById(problemId)).thenReturn(Optional.of(problem));
        when(problemRepository.save(any(Problem.class))).thenReturn(problem);

        // Execução
        ProblemResponse result = problemService.deletarProblem(problemId);

        // Verificação
        assertEquals(problem.getName(), result.getName());
        assertEquals(false, problem.getActive());
        verify(problemRepository, times(1)).findById(problemId);
        verify(problemRepository, times(1)).save(any(Problem.class));
    }
}