package com.creditas.usecase;

import com.creditas.controller.InstallmentResponseMapper;
import com.creditas.controller.model.SimulacaoEmprestimoRequestDTO;
import com.creditas.controller.model.SimulacaoEmprestimoResponseDTO;
import com.creditas.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LoanSimulationUsecase {

    private final EmprestimoService emprestimoService;
    private final InstallmentResponseMapper installmentResponseMapper;

    public SimulacaoEmprestimoResponseDTO simulateLoan(SimulacaoEmprestimoRequestDTO requestDTO) {
        var loanRate = emprestimoService.getLoanRate(requestDTO.dataNascimento());
        var installmentPlan = emprestimoService.calculateLoanConditions(
                BigDecimal.valueOf(requestDTO.loanValue()),
                requestDTO.installments(),
                loanRate
        );


        return installmentResponseMapper.convertToResponse(installmentPlan);
    }

    public SimulacaoEmprestimoResponseDTO saveClientCredentials(SimulacaoEmprestimoRequestDTO requestDTO) {

        return installmentResponseMapper.convertToResponse(installmentPlan);
    }


}
