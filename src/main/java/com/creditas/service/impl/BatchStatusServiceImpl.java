package com.creditas.service.impl;

import com.creditas.service.BatchStatusService;
import com.creditas.usecase.model.BatchStatus;
import com.creditas.usecase.model.BatchStatusUseCaseDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BatchStatusServiceImpl implements BatchStatusService {

    private final ConcurrentHashMap<UUID, BatchStatusUseCaseDTO> batchStatusMap = new ConcurrentHashMap<>();

    @Override
    public UUID createBatch() {
        UUID batchId = UUID.randomUUID();
        batchStatusMap.put(batchId,
                BatchStatusUseCaseDTO.builder()
                        .status(BatchStatus.IN_PROGRESS.toString())
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return batchId;
    }

    @Override
    public void updateBatchStatus(UUID batchId, BatchStatus status) {
        BatchStatusUseCaseDTO batchStatus = batchStatusMap.get(batchId);
        LocalDateTime createdAt = batchStatus.getCreatedAt();
        Duration elapsedTime = Duration.between(createdAt, LocalDateTime.now());

        batchStatusMap.put(batchId, BatchStatusUseCaseDTO.builder()
                .status(status.toString())
                .createdAt(createdAt)
                .elapsedTime(elapsedTime.getSeconds() + " seconds")
                .build()
        );
    }

    @Override
    public BatchStatusUseCaseDTO getBatchStatus(UUID batchId) {
        return batchStatusMap.get(batchId);
    }
}
