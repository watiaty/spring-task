package ru.aston.springtask2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.springtask2.model.Locality;

import java.util.Optional;

public interface LocalityRepository extends JpaRepository<Locality, Long> {
    Optional<Locality> findFirstByNameAndRegion(String name, String region);
}
