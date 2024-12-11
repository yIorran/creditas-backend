package com.creditas.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record InstallmentPlanResponseDTO(@JsonProperty("parcelas") List<InstallmentResponseDTO> parcelas) {


}
