package com.creditas.usecase;

import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.entity.LoanSimulation;
import com.creditas.exception.LoanCalculationException;
import com.creditas.exception.SaveCustomerException;
import com.creditas.service.EmailService;
import com.creditas.service.EmprestimoService;
import com.creditas.service.LoanSimulationService;
import com.creditas.usecase.model.LoanSimulationUseCaseDTO;
import com.creditas.usecase.model.mapper.LoanSimulationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoanSimulationUsecase {

    private final EmprestimoService emprestimoService;
    private final LoanSimulationService loanSimulationService;
    private final EmailService emailService;
    private final LoanSimulationMapper loanSimulationMapper;

    public InstallmentPlanResponseDTO simulateLoan(LoanSimulationUseCaseDTO requestDTO) {
        try {
            log.info("Simulating loan for customer: {}", requestDTO.customerName());
            var rate = emprestimoService.getLoanRate(requestDTO.birthDate());
            var loanSimulation = emprestimoService.calculateLoanConditions(
                    requestDTO.loanValue(),
                    requestDTO.installments(),
                    rate);

            saveLoanSimulation(requestDTO, loanSimulation);
            log.info("Loan simulation completed for customer: {}", requestDTO.customerName());
            return loanSimulationMapper.convertToResponse(loanSimulation);
        } catch (Exception e) {
            throw new LoanCalculationException(e.getMessage());
        }
    }

    @Async
    private void sendEmail(LoanSimulationUseCaseDTO requestDTO, LoanSimulation loanSimulation) {
        log.info("Sending email to customer: {}", requestDTO.customerName());
        emailService.sendEmail(requestDTO.customerEmail(), "Loan Simulation", "Your loan simulation is ready!" + loanSimulation.toString());
        log.info("Email sent to customer: {}", requestDTO.customerName());
    }

    @Async
    private void saveLoanSimulation(LoanSimulationUseCaseDTO requestDTO, LoanSimulation loanSimulation) {
        try {
            Optional.ofNullable(requestDTO.customerEmail()).orElseThrow(() -> new SaveCustomerException("Customer email is required"));
            Optional.ofNullable(requestDTO.customerName()).orElseThrow(() -> new SaveCustomerException("Customer name is required"));
            log.info("Saving loan simulation for customer: {}", requestDTO.customerName());
            loanSimulationService.saveLoanSimulation(
                    requestDTO.birthDate().toString(),
                    requestDTO.customerName(),
                    requestDTO.customerEmail(),
                    loanSimulation
            );
            log.info("Loan simulation saved for customer: {}", requestDTO.customerName());
        } catch (Exception e) {
            throw new SaveCustomerException(e.getMessage());
        }
    }
}