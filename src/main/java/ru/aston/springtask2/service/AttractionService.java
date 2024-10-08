package ru.aston.springtask2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;

public interface AttractionService {
    Attraction createAttraction(Attraction attraction);

    Attraction getById(Long id);

    Page<Attraction> getAllByType(AttractionType type, Pageable pageable);

    Page<Attraction> getAllByLocalityId(Long id, Pageable pageable);

    Attraction updateDescription(Long id, String shortDescription);

    void deleteAttractionById(Long id);
}
