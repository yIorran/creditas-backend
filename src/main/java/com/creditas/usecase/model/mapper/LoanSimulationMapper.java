package com.creditas.usecase.model.mapper;

import com.creditas.controller.model.response.BatchStatusResponseDTO;
import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.entity.LoanSimulation;
import com.creditas.usecase.model.BatchStatusUseCaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanSimulationMapper {

    @Mapping(target = "valorTotalComJuros", source = "valorTotal")
    InstallmentPlanResponseDTO convertToResponse(LoanSimulation installment);

    BatchStatusResponseDTO toBatchResponse(BatchStatusUseCaseDTO batchStatusUseCaseDTO);

}
