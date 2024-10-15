package ru.aston.springtask2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.springtask2.it.AbstractRestControllerBaseTest;
import ru.aston.springtask2.model.Locality;
import ru.aston.springtask2.util.DataUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class LocalityRepositoryTest extends AbstractRestControllerBaseTest {
    @Autowired
    private LocalityRepository localityRepository;

    @Test
    @DisplayName("Test save attraction functionality")
    void givenLocality_whenSave_thenLocalityIsCreated() {
        // given
        Locality locality = DataUtils.getHomelLocalityTransient();
        // when
        Locality savedLocality = localityRepository.save(locality);
        // then
        assertThat(savedLocality).isNotNull();
        assertThat(savedLocality.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test get attraction by id functionality")
    void givenAttractionCreated_whenFindById_thenReturnedAttraction() {
        // given
        Locality locality = DataUtils.getHomelLocalityTransient();
        Locality savedLocality = localityRepository.save(locality);
        // when
        Locality result = localityRepository.findById(savedLocality.getId()).get();
        // then
        assertThat(result).isNotNull();
    }
}
