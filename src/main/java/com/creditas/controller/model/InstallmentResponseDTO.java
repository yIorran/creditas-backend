package com.creditas.controller.model;

import lombok.Builder;

@Builder
public record InstallmentResponseDTO(String parcela, String valor) {

}
