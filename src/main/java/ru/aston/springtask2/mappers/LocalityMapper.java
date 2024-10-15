package ru.aston.springtask2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.aston.springtask2.dtos.request.AddLocalityRequestDTO;
import ru.aston.springtask2.dtos.response.LocalityResponseDto;
import ru.aston.springtask2.model.Locality;

@Mapper
public interface LocalityMapper {
    LocalityMapper INSTANCE = Mappers.getMapper(LocalityMapper.class);

    @Mapping(source = "id", target = "id")
    LocalityResponseDto localityToLocalityResponseDTO(Locality locality);

    Locality addLocalityRequestDTOToLocality(AddLocalityRequestDTO dto);
}
