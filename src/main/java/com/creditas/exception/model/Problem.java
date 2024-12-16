package com.creditas.exception.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@Builder
public class Problem {
    private String message;
    private HttpStatusCode status;
    private String path;
    private String timestamp;
}
