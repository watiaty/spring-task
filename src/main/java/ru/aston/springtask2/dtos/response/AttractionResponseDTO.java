package ru.aston.springtask2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.aston.springtask2.model.AttractionType;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class AttractionResponseDTO {
    private Long id;
    private String name;
    private Date creationDate;
    private String description;
    private AttractionType type;
    private InnerLocalityResponseDTO locality;
}
