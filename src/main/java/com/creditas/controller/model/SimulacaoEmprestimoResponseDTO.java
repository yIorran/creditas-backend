package com.creditas.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record SimulacaoEmprestimoResponseDTO(@JsonProperty("parcelas") List<InstallmentResponseDTO> parcelas) {


}
