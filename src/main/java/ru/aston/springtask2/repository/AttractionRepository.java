package ru.aston.springtask2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    Page<Attraction> findAllByType(AttractionType type, Pageable pageable);

    Page<Attraction> findAllByLocalityId(Long id, Pageable pageable);
}
