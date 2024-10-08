package ru.aston.springtask2.dtos.request;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetWeatherRequestDTO {
    @Positive(message = "Долгота должно быть положительным")
    private Double longitude;
    @Positive(message = "Широта должно быть положительным")
    private Double latitude;
}
