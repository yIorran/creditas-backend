package com.creditas.exception;

import com.creditas.exception.model.Problem;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class CreditasExceptionHandler {

    @ExceptionHandler(value = {LoanCalculationException.class, RateRecoveryException.class, SaveCustomerException.class})
    public ResponseEntity<Problem> handleCustomExceptions(HttpServletRequest req, Exception e) throws Exception {
        HttpStatus status = Objects.requireNonNull(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)).value();
        Problem problem = Problem.builder()
                .message(e.getMessage())
                .status(status)
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now().toString())
                .build();
        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Problem> requestCustomExceptions(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        String combinedErrorMessage = String.join(", ", errorMessages);

        Problem problem = Problem.builder()
                .message(combinedErrorMessage)
                .status(HttpStatus.BAD_REQUEST)
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(BatchLoanCalculationException.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(Problem.builder()
                .message("Simulações serão enviadas por email pela alta demanda")
                .status(HttpStatus.OK), HttpStatus.OK);
    }
}
