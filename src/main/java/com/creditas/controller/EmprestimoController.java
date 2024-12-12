package com.creditas.controller;


import com.creditas.controller.mapper.LoanRequestMapper;
import com.creditas.controller.model.request.LoanSimulationRequestDTO;
import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.usecase.LoanSimulationUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/emprestimo")
@AllArgsConstructor
public class EmprestimoController {

    private LoanSimulationUsecase emprestimoService;
    private final LoanRequestMapper mapper;

    @GetMapping
    public ResponseEntity<InstallmentPlanResponseDTO> simularEmprestimo(@RequestBody LoanSimulationRequestDTO request) throws ExecutionException, InterruptedException {
        var response = emprestimoService.simulateLoan(mapper.convertToUseCaseRequest(request));

        return ResponseEntity.ok(response);
    }

}

