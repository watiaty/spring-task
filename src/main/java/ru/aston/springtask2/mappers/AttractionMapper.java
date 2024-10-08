package ru.aston.springtask2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.springtask2.dtos.request.AddAttractionRequestDTO;
import ru.aston.springtask2.dtos.response.AttractionResponseDTO;
import ru.aston.springtask2.model.Attraction;

import java.util.List;

@Mapper
public interface AttractionMapper {

    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    AttractionResponseDTO attractionToAttractionResponseDTO(Attraction attraction);

    List<AttractionResponseDTO> attractionListToAttractionListResponseDTO(List<Attraction> attractions);

    Attraction attractionResponseDTOToAttraction(AddAttractionRequestDTO dto);
}
