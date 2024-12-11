package com.creditas.service.impl;

import com.creditas.usecase.model.mapper.LoanSimulationMapper;
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
    private final LoanSimulationMapper installmentResponseMapper;


    public InstallmentPlan calculateLoanConditions(BigDecimal loanValue, Integer installs, BigDecimal rate) {
        return solver.calculateInstallmentPlan(
                loanValue,
                rate,
                installs
        );
    }

    public BigDecimal getLoanRate(LocalDate birthDate) {
        var rate = taxaRepository.findBetween(Utils.calcularIdade(birthDate));
        return new BigDecimal(rate);
    }
}
