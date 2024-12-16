package com.creditas.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchStatusResponseDTO {

    private String status;
    private String createdAt;
    private String elapsedTime;

}
