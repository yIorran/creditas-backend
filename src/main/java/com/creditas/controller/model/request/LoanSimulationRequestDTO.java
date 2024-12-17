package com.creditas.controller.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LoanSimulationRequestDTO(@NotNull(message = "loanValue must not be null") @Min(value = 1000, message = "loan value must be greater than 1000") Double loanValue,
                                       @NotNull(message = "birthDate must not be null") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                                       @NotNull(message = "installments must not be null") @Min(value = 12, message = "installments must be greater than 12") Integer installments,
                                       @NotEmpty(message = "customerEmail must not be null") String customerEmail,
                                       @NotEmpty(message = "customerName must not be null") String customerName) {

}
