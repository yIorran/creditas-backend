package com.creditas.service;

import com.creditas.entity.LoanSimulation;

import java.math.BigDecimal;

public interface CalculationService {

    LoanSimulation calculateInstallmentPlan(BigDecimal loanValue, BigDecimal anualRate, Integer installs);

}
