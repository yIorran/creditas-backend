package com.creditas.usecase.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchStatusUseCaseDTO {

    private String status;
    private String elapsedTime;
    private LocalDateTime createdAt;

}
