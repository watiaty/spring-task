package ru.aston.springtask2.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocalityResponseDto {
    private Long id;
    private String name;
    private String region;
    private Double latitude;
    private Double longitude;
}
