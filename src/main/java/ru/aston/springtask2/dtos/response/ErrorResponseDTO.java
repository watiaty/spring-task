package ru.aston.springtask2.dtos.response;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String message;
    private String status;
}
