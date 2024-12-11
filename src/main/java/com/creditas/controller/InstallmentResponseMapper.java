package com.creditas.controller;

import com.creditas.controller.model.SimulacaoEmprestimoResponseDTO;
import com.creditas.entity.InstallmentPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface InstallmentResponseMapper {

    @Mapping(target = "parcelas", source = "parcelas")
    SimulacaoEmprestimoResponseDTO convertToResponse(InstallmentPlan installment);


}
