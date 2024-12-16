package com.creditas.service.impl;

import com.creditas.entity.LoanSimulation;
import com.creditas.service.CalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Override
    public LoanSimulation calculateInstallmentPlan(BigDecimal loanValue, BigDecimal anualRate, Integer installs) {
        var anualRateDecimal = anualRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP); // ajustar taxa para porcentagem

        var monthRate = anualRateDecimal.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP); // taxa de juros mensal

        var onePlusRate = BigDecimal.ONE.add(monthRate);
        var powerTerm = onePlusRate.pow(installs);
        var denominator = powerTerm.subtract(BigDecimal.ONE);
        var pmt = loanValue.multiply(monthRate).multiply(powerTerm).divide(denominator, 2, RoundingMode.HALF_UP);

        var plan = new LoanSimulation();

        plan.setQuantidadeParcelas(installs.toString());
        plan.setValorParcelas(pmt.setScale(2, RoundingMode.HALF_UP));
        plan.setValorTotal(pmt.multiply(new BigDecimal(installs)).setScale(2, RoundingMode.HALF_UP));
        plan.setRate(anualRate.doubleValue());

        return plan;
    }


}
