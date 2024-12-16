package com.creditas.controller;

import com.creditas.controller.mapper.LoanRequestMapper;
import com.creditas.controller.model.request.LoanSimulationRequestDTO;
import com.creditas.controller.model.response.BatchStatusResponseDTO;
import com.creditas.usecase.BatchLoanSimulationUsecase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/batch")
@AllArgsConstructor
public class BatchController {

    private final BatchLoanSimulationUsecase batchLoanSimulationUsecase;
    private final LoanRequestMapper mapper;

    @PostMapping("/simulate")
    public ResponseEntity<UUID> batchSimulateLoan(@Valid @RequestBody List<LoanSimulationRequestDTO> request) {
        var useCaseRequests = request.stream()
                .map(mapper::convertToUseCaseRequest)
                .toList();
        UUID batchId = batchLoanSimulationUsecase.batchSimulateLoan(useCaseRequests);
        return ResponseEntity.ok(batchId);
    }

    @GetMapping("/status/{batchId}")
    public ResponseEntity<BatchStatusResponseDTO> getBatchStatus(@PathVariable UUID batchId) {
        var status = batchLoanSimulationUsecase.getBatchSimulateLoanStatus(batchId);
        return ResponseEntity.ok(status);
    }
}
