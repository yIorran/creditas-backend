package com.creditas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RateRecoveryException extends RuntimeException {
    public RateRecoveryException(String message) {
        super(message);
    }
}
