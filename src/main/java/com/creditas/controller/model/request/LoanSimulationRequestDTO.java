package com.creditas.controller.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LoanSimulationRequestDTO(@NotNull Double loanValue,
                                       @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                                       @NotNull Integer installments,
                                       @NotNull String customerEmail,
                                       @NotNull String customerName) {

}
