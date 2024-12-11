package com.creditas.controller.model.response;

import lombok.Builder;

@Builder
public record InstallmentResponseDTO(String parcela, String valor) {

}
