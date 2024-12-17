package com.creditas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SaveCustomerException extends RuntimeException {
    public SaveCustomerException(String message) {
        super(message);
    }
}
