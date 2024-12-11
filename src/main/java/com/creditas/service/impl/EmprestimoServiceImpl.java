package com.creditas.service.impl;

import com.creditas.controller.InstallmentResponseMapper;
import com.creditas.controller.model.SimulacaoEmprestimoRequestDTO;
import com.creditas.controller.model.SimulacaoEmprestimoResponseDTO;
import com.creditas.entity.InstallmentPlan;
import com.creditas.repository.GetTaxaRepository;
import com.creditas.service.EmprestimoService;
import com.creditas.service.Solver;
import com.creditas.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final GetTaxaRepository getTaxaRepository;
    private final Solver solver;
    private final InstallmentResponseMapper installmentResponseMapper;

    @Override
    public SimulacaoEmprestimoResponseDTO simularEmprestimo(SimulacaoEmprestimoRequestDTO requestDTO) {
        var rate = getTaxaRepository.findBetween(Utils.calcularIdade(requestDTO.dataNascimento()));
        var installmentPlan = solver.calculateInstallmentPlan(
                BigDecimal.valueOf(requestDTO.valorEmprestimo()),
                new BigDecimal(rate),
                requestDTO.prazoMeses()
        );
        return installmentResponseMapper.convertToResponse(installmentPlan);
    }
}
