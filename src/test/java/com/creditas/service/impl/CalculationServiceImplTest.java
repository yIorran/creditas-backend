package com.creditas.service.impl;

import com.creditas.entity.LoanSimulation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationServiceImplTest {

    private final CalculationServiceImpl calculationService = new CalculationServiceImpl();

    @Test
    public void testCalculateInstallmentPlan() {
        BigDecimal loanValue = new BigDecimal("10000");
        BigDecimal annualRate = new BigDecimal("5");
        int installs = 12;

        LoanSimulation result = calculationService.calculateInstallmentPlan(loanValue, annualRate, installs);

        assertEquals("12", result.getQuantidadeParcelas());
        assertEquals(new BigDecimal("856.07"), result.getValorParcelas());
        assertEquals(new BigDecimal("10272.84"), result.getValorTotal());
        assertEquals(5.0, result.getRate());
    }
}
