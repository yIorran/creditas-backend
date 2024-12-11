package com.creditas.service;

import com.creditas.entity.InstallmentPlan;

import java.math.BigDecimal;

public interface CalculationService {

    InstallmentPlan calculateInstallmentPlan(BigDecimal loanValue, BigDecimal anualRate, Integer installs);

}
