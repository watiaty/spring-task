package ru.aston.springtask2.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddLocalityRequestDTO {
    @NotBlank(message = "Название не должно быть пустым")
    private String name;
    @NotBlank(message = "Регион не должен быть пустым")
    private String region;
    @Positive(message = "Широта должно быть положительным")
    private Double latitude;
    @Positive(message = "Долгота должно быть положительным")
    private Double longitude;
}
