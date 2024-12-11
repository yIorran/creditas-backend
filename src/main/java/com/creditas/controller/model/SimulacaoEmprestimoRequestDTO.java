package com.creditas.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SimulacaoEmprestimoRequestDTO(Double valorEmprestimo,
                                            @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento,
                                            Integer prazoMeses,
                                            String email) {

}
