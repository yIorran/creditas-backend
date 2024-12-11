package com.creditas.usecase.model.mapper;

import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.entity.InstallmentPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanSimulationMapper {

    @Mapping(target = "parcelas", source = "parcelas")
    InstallmentPlanResponseDTO convertToResponse(InstallmentPlan installment);

}
