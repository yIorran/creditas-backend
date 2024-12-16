package com.creditas.service.impl;

import com.creditas.entity.LoanSimulation;
import com.creditas.exception.LoanCalculationException;
import com.creditas.exception.RateRecoveryException;
import com.creditas.repository.RateRepository;
import com.creditas.service.CalculationService;
import com.creditas.service.EmprestimoService;
import com.creditas.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final RateRepository taxaRepository;
    private final CalculationService solver;

    @Override
    public LoanSimulation calculateLoanConditions(BigDecimal loanValue, Integer installs, BigDecimal rate) {
        try {
            return solver.calculateInstallmentPlan(loanValue, rate, installs);
        } catch (Exception e) {
            throw new LoanCalculationException("Error calculating loan conditions");
        }
    }

    //  @Cacheable("loanRate", ttl = 1h) // cachear essa chamada para tirar carga do banco de dados.
    public BigDecimal getLoanRate(LocalDate birthDate) {
        Assert.notNull(birthDate, "Birth date must not be null");

        try {
            var rate = taxaRepository.findBetween(Utils.calcularIdade(birthDate));
            return new BigDecimal(rate);
        } catch (Exception e) {
            throw new RateRecoveryException("Erro ao buscar taxa de juros");
        }
    }
}
