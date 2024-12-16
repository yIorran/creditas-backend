package com.creditas.controller;


import com.creditas.controller.mapper.LoanRequestMapper;
import com.creditas.controller.model.request.LoanSimulationRequestDTO;
import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.usecase.LoanSimulationUsecase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emprestimo")
@AllArgsConstructor
public class EmprestimoController {

    private final LoanSimulationUsecase loanSimulationUsecase;
    private final LoanRequestMapper mapper;

    @PostMapping
    public ResponseEntity<InstallmentPlanResponseDTO> simularEmprestimo(@Valid @RequestBody LoanSimulationRequestDTO request) {
        var response = loanSimulationUsecase.simulateLoan(mapper.convertToUseCaseRequest(request));
        return ResponseEntity.ok(response);
    }

}

