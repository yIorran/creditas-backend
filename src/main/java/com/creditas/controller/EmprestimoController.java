package com.creditas.controller;


import com.creditas.controller.model.SimulacaoEmprestimoRequestDTO;
import com.creditas.controller.model.SimulacaoEmprestimoResponseDTO;
import com.creditas.service.EmprestimoService;
import com.creditas.usecase.LoanSimulationUsecase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/emprestimo")
@AllArgsConstructor
public class EmprestimoController {

    private LoanSimulationUsecase emprestimoService;

    @GetMapping
    public ResponseEntity<SimulacaoEmprestimoResponseDTO> simularEmprestimo(@RequestBody SimulacaoEmprestimoRequestDTO request) {
        var simulacaoEmprestimoResponseDTO = emprestimoService.simulateLoan(request);

        return ResponseEntity.ok(simulacaoEmprestimoResponseDTO);
    }

}

