package com.creditas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BatchLoanCalculationException extends RuntimeException {
    public BatchLoanCalculationException(String message) {
        super(message);
    }
}
