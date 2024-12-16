package com.creditas.service;

import com.creditas.entity.LoanSimulation;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface EmprestimoService {

    LoanSimulation calculateLoanConditions(BigDecimal loanValue, Integer installs, BigDecimal rate);

    BigDecimal getLoanRate(LocalDate birthDate);
}
