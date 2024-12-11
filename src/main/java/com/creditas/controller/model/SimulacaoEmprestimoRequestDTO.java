package com.creditas.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SimulacaoEmprestimoRequestDTO(@NotNull Double loanValue,
                                            @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento,
                                            @NotNull Integer installments,
                                            @NotNull String customerEmail,
                                            @NotNull String customerName) {

}
