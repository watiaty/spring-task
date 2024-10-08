package ru.aston.springtask2.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.springtask2.dtos.request.AddAttractionRequestDTO;
import ru.aston.springtask2.dtos.response.AttractionResponseDTO;
import ru.aston.springtask2.mappers.AttractionMapper;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;
import ru.aston.springtask2.service.AttractionService;
import ru.aston.springtask2.service.LocalityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attraction")
public class AttractionController {

    private final AttractionService attractionService;
    private final LocalityService localityService;

    /**
     * Добавляет новую достопримечательность
     *
     * @param dto Содержит в себе данные для создания нового Attraction.
     * @return Возвращает DTO объекта Attraction и статус 201
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавление достопримечательности", description = "Добавить достопримечательность")
    public ResponseEntity<AttractionResponseDTO> createAttraction(@RequestBody AddAttractionRequestDTO dto) {
        Attraction attraction = AttractionMapper.INSTANCE.attractionResponseDTOToAttraction(dto);
        attraction.setLocality(localityService.findById(dto.getLocalityId()));
        attraction.setAssistance(null);
        attraction = attractionService.createAttraction(attraction);

        return new ResponseEntity<>(AttractionMapper.INSTANCE.attractionToAttractionResponseDTO(attraction), HttpStatus.CREATED);
    }


    /**
     * Получает отсортированный и отфильтрованный список достопримечательностей
     *
     * @param pageable Объект для управления пагинацией и сортировкой, по умолчанию сортировка по полю name
     * @param type     Значение перечисления, для фильтрации
     * @return Возвращает страницу с DTO достопримечательностей и статус 200
     */
    @GetMapping
    @Operation(summary = "Получить список достопремечательностей", description = """
            Получить все достопримечательности (передать параметр для сортировки по
            наименованию достопримечательности, параметр для фильтрации по типу
            достопримечательности
            """)
    public ResponseEntity<Page<AttractionResponseDTO>> getAttractionsByType(@PageableDefault(sort = "name") Pageable pageable, @RequestParam AttractionType type) {
        Page<Attraction> attractions = attractionService.getAllByType(type, pageable);

        Page<AttractionResponseDTO> attractionDTOs = attractions.map(AttractionMapper.INSTANCE::attractionToAttractionResponseDTO);

        return new ResponseEntity<>(attractionDTOs, HttpStatus.OK);
    }

    /**
     * Получает список достопримечательностей по полю name класса Locality
     *
     * @param locality значению для поиске по полю name класса Locality
     * @return Возвращает список достопримечательностей и статус 200
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить список достопремечательностей по местоположению", description = """
            Получить все достопримечательности конкретного местоположения (Locality)
            """)
    public ResponseEntity<Page<AttractionResponseDTO>> getAttractionsByLocalityName(Pageable pageable, @PathVariable Long id) {
        Page<Attraction> attractions = attractionService.getAllByLocalityId(id, pageable);

        Page<AttractionResponseDTO> attractionsDTOs = attractions.map(AttractionMapper.INSTANCE::attractionToAttractionResponseDTO);
        return new ResponseEntity<>(attractionsDTOs, HttpStatus.OK);
    }

    /**
     * Изменяет описание достопримечательности
     *
     * @param id          ID объекта для изменения
     * @param description Новое описание
     * @return Возвращает DTO измененной достопримечательности
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Изменение достопримечательности", description = """
            Изменение данных по достопримечательности (возможно изменение только
            поля Краткое описание)
            """)
    public ResponseEntity<AttractionResponseDTO> updateDescription(@PathVariable Long id, @RequestParam String description) {
        Attraction attraction = attractionService.updateDescription(id, description);

        AttractionResponseDTO attractionResponseDTO = AttractionMapper.INSTANCE.attractionToAttractionResponseDTO(attraction);
        return new ResponseEntity<>(attractionResponseDTO, HttpStatus.OK);
    }

    /**
     * Удаляет достопримечательность
     *
     * @param id ID объекта для удаления
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", description = "Удаление достопримечательности из справочника")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        attractionService.deleteAttractionById(id);
    }
}
