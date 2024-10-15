package ru.aston.springtask2.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import ru.aston.springtask2.model.Assistance;
import ru.aston.springtask2.model.AttractionType;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class AddAttractionRequestDTO {
    @NotBlank(message = "Название не должно быть пустым")
    private String name;
    private Date creationDate;
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;
    private AttractionType type;
    @Positive(message = "ID должно быть положительным")
    private Long localityId;
    private List<Assistance> assistance;
}
