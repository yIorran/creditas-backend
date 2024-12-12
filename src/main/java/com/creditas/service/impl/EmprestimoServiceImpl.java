package com.creditas.service.impl;

import com.creditas.entity.LoanSimulation;
import com.creditas.repository.RateRepository;
import com.creditas.service.CalculationService;
import com.creditas.service.EmprestimoService;
import com.creditas.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final RateRepository taxaRepository;
    private final CalculationService solver;


    public LoanSimulation calculateLoanConditions(BigDecimal loanValue, Integer installs, BigDecimal rate) {
        return solver.calculateInstallmentPlan(
                loanValue,
                rate,
                installs
        );
    }

    //  @Cacheable("loanRate", ttl = 1h) // cachear essa chamada para tirar carga do banco de dados.
    public BigDecimal getLoanRate(LocalDate birthDate) {
        var rate = taxaRepository.findBetween(Utils.calcularIdade(birthDate));
        return new BigDecimal(rate);
    }
}
