package com.creditas.controller.mapper;

import com.creditas.controller.model.request.LoanSimulationRequestDTO;
import com.creditas.usecase.model.LoanSimulationUseCaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanRequestMapper {

    @Mapping(target = "loanValue", expression = "java(new java.math.BigDecimal(request.loanValue()))")
    LoanSimulationUseCaseDTO convertToUseCaseRequest(LoanSimulationRequestDTO request);


}
