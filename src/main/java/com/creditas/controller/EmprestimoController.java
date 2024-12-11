package com.creditas.controller;


import com.creditas.controller.model.SimulacaoEmprestimoRequestDTO;
import com.creditas.controller.model.SimulacaoEmprestimoResponseDTO;
import com.creditas.service.EmprestimoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emprestimo")
@AllArgsConstructor
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<SimulacaoEmprestimoResponseDTO> simularEmprestimo(@RequestBody SimulacaoEmprestimoRequestDTO request) {
        var simulacaoEmprestimoResponseDTO = emprestimoService.simularEmprestimo(SimulacaoEmprestimoRequestDTO.builder()
                .valorEmprestimo(request.valorEmprestimo())
                .dataNascimento(request.dataNascimento())
                .prazoMeses(request.prazoMeses()).build()
        );
        return ResponseEntity.ok(simulacaoEmprestimoResponseDTO);
    }

}

