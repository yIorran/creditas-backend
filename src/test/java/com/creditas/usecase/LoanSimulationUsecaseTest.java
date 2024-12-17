package com.creditas.usecase;

import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.entity.LoanSimulation;
import com.creditas.exception.LoanCalculationException;
import com.creditas.service.EmailService;
import com.creditas.service.EmprestimoService;
import com.creditas.service.LoanSimulationService;
import com.creditas.usecase.model.LoanSimulationUseCaseDTO;
import com.creditas.usecase.model.mapper.LoanSimulationMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanSimulationUsecaseTest {

    public static final String MAIL = "example@hotmail.com";
    public static final BigDecimal LOAN_VALUE = BigDecimal.valueOf(1000);
    public static final Integer INSTALLMENTS = 10;
    public static final String CUSTOMER_NAME = "Iorran";
    public static final BigDecimal RATE = BigDecimal.valueOf(0.05);
    public static final LocalDate BIRTH_DATE = LocalDate.of(2001, 12, 5);
    @Mock
    private EmprestimoService emprestimoService;

    @Mock
    private LoanSimulationService loanSimulationService;

    @Mock
    private EmailService emailService;

    @Mock
    private LoanSimulationMapper loanSimulationMapper;

    @InjectMocks
    private LoanSimulationUsecase loanSimulationUsecase;


    @Test
    void testSimulateLoanAndSendEmail() {
        LoanSimulationUseCaseDTO requestDTO = LoanSimulationUseCaseDTO.builder()
                .birthDate(BIRTH_DATE)
                .customerEmail(MAIL)
                .loanValue(LOAN_VALUE)
                .installments(INSTALLMENTS)
                .customerName(CUSTOMER_NAME)
                .build();
        LoanSimulation loanSimulation = new LoanSimulation();
        loanSimulation.setRate(RATE.doubleValue());
        loanSimulation.setValorTotal(LOAN_VALUE);
        loanSimulation.setQuantidadeParcelas(INSTALLMENTS.toString());
        loanSimulation.setValorParcelas(BigDecimal.valueOf(100));
        InstallmentPlanResponseDTO responseDTO = InstallmentPlanResponseDTO.builder()
                .quantidadeParcelas("100")
                .valorParcelas("100")
                .valorTotalComJuros("1001")
                .build();

        when(emprestimoService.getLoanRate(BIRTH_DATE)).thenReturn(RATE);
        when(emprestimoService.calculateLoanConditions(LOAN_VALUE, INSTALLMENTS, RATE)).thenReturn(loanSimulation);
        when(loanSimulationMapper.convertToResponse(loanSimulation)).thenReturn(responseDTO);

        InstallmentPlanResponseDTO result = loanSimulationUsecase.simulateLoan(requestDTO);

        assertEquals(responseDTO, result);
        verify(emprestimoService, times(1)).getLoanRate(any());
        verify(emprestimoService, times(1)).calculateLoanConditions(any(BigDecimal.class), anyInt(), any(BigDecimal.class));
        verify(loanSimulationMapper, times(1)).convertToResponse(loanSimulation);
    }

    @Test
    void testSimulateLoanFailure() {
        LoanSimulationUseCaseDTO requestDTO = LoanSimulationUseCaseDTO.builder().build();

        when(emprestimoService.getLoanRate(any())).thenThrow(new RuntimeException());

        assertThrows(LoanCalculationException.class, () -> loanSimulationUsecase.simulateLoan(requestDTO));
        verify(emprestimoService, times(1)).getLoanRate(any());
    }

}
