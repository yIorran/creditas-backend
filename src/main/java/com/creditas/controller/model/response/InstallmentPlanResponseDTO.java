package com.creditas.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record InstallmentPlanResponseDTO(@JsonProperty("quantidadeParcelas") String quantidadeParcelas,
                                         @JsonProperty("valorParcelas") String valorParcelas,
                                         @JsonProperty("totalComJuros") String valorTotalComJuros) {


}
