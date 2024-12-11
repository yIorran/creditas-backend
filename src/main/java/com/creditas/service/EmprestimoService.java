package com.creditas.service;

import com.creditas.controller.model.SimulacaoEmprestimoRequestDTO;
import com.creditas.controller.model.SimulacaoEmprestimoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmprestimoService {

    SimulacaoEmprestimoResponseDTO simularEmprestimo(SimulacaoEmprestimoRequestDTO simulacaoEmprestimoRequestDTO);

}
