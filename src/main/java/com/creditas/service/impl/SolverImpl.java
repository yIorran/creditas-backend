package com.creditas.service.impl;

import com.creditas.entity.Installment;
import com.creditas.entity.InstallmentPlan;
import com.creditas.service.CalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolverImpl implements CalculationService {

    @Override
    public InstallmentPlan calculateInstallmentPlan(BigDecimal loanValue, BigDecimal anualRate, Integer installs) {
        BigDecimal anualRateDecimal = anualRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);

        BigDecimal monthRate = anualRateDecimal.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);

        BigDecimal onePlusRate = BigDecimal.ONE.add(monthRate);
        BigDecimal powerTerm = onePlusRate.pow(installs);
        BigDecimal denominator = powerTerm.subtract(BigDecimal.ONE);
        BigDecimal pmt = loanValue.multiply(monthRate).divide(denominator, 2, RoundingMode.HALF_UP);

        InstallmentPlan plan = new InstallmentPlan();

        plan.setParcelas(calculateInstallments(pmt, installs));

        return plan;
    }

    private List<Installment> calculateInstallments(BigDecimal pmt, Integer installs) {
        List<Installment> installments = new ArrayList<>();

        for (int i = 0; i < installs; i++) {
            Installment installment = new Installment();
            installment.setParcela("Parcela " + (i + 1));
            installment.setValor(pmt.setScale(2, RoundingMode.HALF_UP).toString());

            installments.add(installment);
        }

        return installments;
    }



}
