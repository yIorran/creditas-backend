package com.creditas.service;

import com.creditas.usecase.model.BatchStatus;
import com.creditas.usecase.model.BatchStatusUseCaseDTO;

import java.util.UUID;

public interface BatchStatusService {

    UUID createBatch();

    void updateBatchStatus(UUID batchId, BatchStatus status);

    BatchStatusUseCaseDTO getBatchStatus(UUID batchId);

}
