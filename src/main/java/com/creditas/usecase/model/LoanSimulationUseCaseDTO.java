package com.creditas.usecase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record LoanSimulationUseCaseDTO(@NotNull BigDecimal loanValue,
                                       @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                                       @NotNull Integer installments,
                                       @NotNull String customerEmail,
                                       @NotNull String customerName) {


}
