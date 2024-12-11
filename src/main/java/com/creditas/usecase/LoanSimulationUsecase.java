package com.creditas.usecase;

import com.creditas.controller.model.request.LoanSimulationRequestDTO;
import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.entity.InstallmentPlan;
import com.creditas.service.EmprestimoService;
import com.creditas.usecase.model.LoanSimulationUseCaseDTO;
import com.creditas.usecase.model.mapper.LoanSimulationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class LoanSimulationUsecase {

    private final EmprestimoService emprestimoService;
    private final LoanSimulationMapper installmentResponseMapper;

    @Async
    public CompletableFuture<InstallmentPlanResponseDTO> simulateLoan(LoanSimulationUseCaseDTO requestDTO) {
        CompletableFuture<BigDecimal> loanRateFuture = CompletableFuture.supplyAsync(() ->
                emprestimoService.getLoanRate(requestDTO.birthDate())
        );

        CompletableFuture<InstallmentPlan> installmentPlanFuture = loanRateFuture.thenApplyAsync(loanRate ->
                emprestimoService.calculateLoanConditions(
                        requestDTO.loanValue(),
                        requestDTO.installments(),
                        loanRate
                )
        );

        return installmentPlanFuture.thenApplyAsync(installmentResponseMapper::convertToResponse);
    }

    public InstallmentPlanResponseDTO saveClientCredentials(LoanSimulationRequestDTO requestDTO) {
        return null;
    }


}
