package com.creditas.service;

import com.creditas.entity.InstallmentPlan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public interface EmprestimoService {

    InstallmentPlan calculateLoanConditions(BigDecimal loanValue, Integer installs, String rate);

    String getLoanRate(LocalDate birthDate);
}
