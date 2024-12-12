package com.creditas.usecase;

import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.entity.LoanSimulation;
import com.creditas.service.EmprestimoService;
import com.creditas.service.LoanSimulationService;
import com.creditas.usecase.model.LoanSimulationUseCaseDTO;
import com.creditas.usecase.model.mapper.LoanSimulationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanSimulationUsecase {

    private final EmprestimoService emprestimoService;
    private final LoanSimulationService loanSimulationService;
    private final LoanSimulationMapper loanSimulationMapper;

    public InstallmentPlanResponseDTO simulateLoan(LoanSimulationUseCaseDTO requestDTO) {
        var rate = emprestimoService.getLoanRate(requestDTO.birthDate());
        var loanSimulation = emprestimoService.calculateLoanConditions(
                requestDTO.loanValue(),
                requestDTO.installments(),
                rate);

        saveLoanSimulation(requestDTO, loanSimulation, rate.doubleValue());

        return loanSimulationMapper.convertToResponse(loanSimulation);
    }

    @Async
    private void saveLoanSimulation(LoanSimulationUseCaseDTO requestDTO, LoanSimulation loanSimulation, Double rate) {
        loanSimulationService.saveLoanSimulation(
                requestDTO.birthDate().toString(),
                requestDTO.customerName(),
                requestDTO.customerEmail(),
                loanSimulation
        );
    }

}