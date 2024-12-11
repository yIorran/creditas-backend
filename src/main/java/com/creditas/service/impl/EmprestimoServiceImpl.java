package com.creditas.service.impl;

import com.creditas.controller.InstallmentResponseMapper;
import com.creditas.entity.InstallmentPlan;
import com.creditas.repository.RateRepository;
import com.creditas.service.EmprestimoService;
import com.creditas.service.Solver;
import com.creditas.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final RateRepository taxaRepository;
    private final Solver solver;
    private final InstallmentResponseMapper installmentResponseMapper;


    public InstallmentPlan calculateLoanConditions(BigDecimal loanValue, Integer installs, String rate) {
        return solver.calculateInstallmentPlan(
                loanValue,
                new BigDecimal(rate),
                installs
        );
    }

    public String getLoanRate(LocalDate birthDate) {
        return taxaRepository.findBetween(Utils.calcularIdade(birthDate));
    }
}
