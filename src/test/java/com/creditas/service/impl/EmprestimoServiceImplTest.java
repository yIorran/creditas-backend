package com.creditas.service.impl;

import com.creditas.entity.LoanSimulation;
import com.creditas.exception.LoanCalculationException;
import com.creditas.repository.RateRepository;
import com.creditas.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class EmprestimoServiceImplTest {

    @Mock
    private RateRepository rateRepository;

    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private EmprestimoServiceImpl emprestimoService;

    public EmprestimoServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateLoanConditions() {
        BigDecimal loanValue = new BigDecimal("10000");
        int installs = 12;
        BigDecimal rate = new BigDecimal("5");

        LoanSimulation loanSimulation = new LoanSimulation();
        when(calculationService.calculateInstallmentPlan(loanValue, rate, installs)).thenReturn(loanSimulation);

        LoanSimulation result = emprestimoService.calculateLoanConditions(loanValue, installs, rate);

        assertEquals(loanSimulation, result);
    }

    @Test
    public void testCalculateLoanConditionsException() {
        BigDecimal loanValue = new BigDecimal("10000");
        int installs = 12;
        BigDecimal rate = new BigDecimal("5");

        when(calculationService.calculateInstallmentPlan(loanValue, rate, installs)).thenThrow(new RuntimeException());

        assertThrows(LoanCalculationException.class, () -> emprestimoService.calculateLoanConditions(loanValue, installs, rate));
    }

    @Test
    public void testGetLoanRate() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        when(rateRepository.findBetween(anyInt())).thenReturn(5.0);

        BigDecimal result = emprestimoService.getLoanRate(birthDate);

        assertEquals(new BigDecimal("5"), result);
    }
}
