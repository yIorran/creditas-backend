package com.creditas.usecase;

import com.creditas.controller.model.response.BatchStatusResponseDTO;
import com.creditas.controller.model.response.InstallmentPlanResponseDTO;
import com.creditas.service.BatchStatusService;
import com.creditas.usecase.model.BatchStatus;
import com.creditas.usecase.model.LoanSimulationUseCaseDTO;
import com.creditas.usecase.model.mapper.LoanSimulationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchLoanSimulationUsecase {

    private final LoanSimulationUsecase loanSimulationUsecase;
    private final BatchStatusService batchStatusService;
    private final LoanSimulationMapper loanSimulationMapper;

    public UUID batchSimulateLoan(List<LoanSimulationUseCaseDTO> requests) {
        UUID batchId = batchStatusService.createBatch();
        processBatchSimulationsAsync(requests, batchId);
        return batchId;
    }

    public BatchStatusResponseDTO getBatchSimulateLoanStatus(UUID uuid) {
        return loanSimulationMapper.toBatchResponse(batchStatusService.getBatchStatus(uuid));
    }

    @Async
    public void processBatchSimulationsAsync(List<LoanSimulationUseCaseDTO> requests, UUID batchId) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int chunkSize = (int) Math.ceil((double) requests.size() / availableProcessors);
        List<CompletableFuture<List<InstallmentPlanResponseDTO>>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);

        for (int i = 0; i < availableProcessors; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, requests.size());
            if (start >= requests.size()) break;
            List<LoanSimulationUseCaseDTO> sublist = requests.subList(start, end);

            CompletableFuture<List<InstallmentPlanResponseDTO>> future = CompletableFuture.supplyAsync(() ->
                    sublist.stream()
                            .map(loanSimulationUsecase::simulateLoan)
                            .collect(Collectors.toList()), executor
            );

            futures.add(future);
        }

        try {
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allOf.thenRun(() -> batchStatusService.updateBatchStatus(batchId, BatchStatus.COMPLETED));
        } catch (Exception e) {
            batchStatusService.updateBatchStatus(batchId, BatchStatus.FAILED);
        } finally {
            executor.shutdown();
        }
    }
}
