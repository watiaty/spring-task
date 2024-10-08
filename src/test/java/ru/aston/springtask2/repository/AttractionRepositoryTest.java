package ru.aston.springtask2.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.aston.springtask2.it.AbstractRestControllerBaseTest;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;
import ru.aston.springtask2.util.DataUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AttractionRepositoryTest extends AbstractRestControllerBaseTest {

    @Autowired
    private AttractionRepository attractionRepository;

    @Test
    @DisplayName("Test save attraction functionality")
    void givenAttraction_whenSave_thenAttractionIsCreated() {
        // given
        Attraction attraction = DataUtils.getHomelParkTransient();
        // when
        Attraction savedAttraction = attractionRepository.save(attraction);
        // then
        assertThat(savedAttraction).isNotNull();
        assertThat(savedAttraction.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test update description functionality")
    void givenAttractionPersistAndDescription_whenUpdateDescription_thenAttractionDescriptionUpdated() {
        // given
        Attraction attraction = DataUtils.getHomelParkTransient();
        Attraction savedAttraction = attractionRepository.save(attraction);
        String newDescription = "new description";
        // when
        savedAttraction.setDescription(newDescription);
        Attraction result = attractionRepository.save(savedAttraction);
        // then
        assertThat(result.getDescription()).isEqualTo(newDescription);
    }

    @Test
    @DisplayName("Test get attraction by id functionality")
    void givenAttractionCreated_whenFindById_thenReturnedAttraction() {
        // given
        Attraction attraction = DataUtils.getHomelParkTransient();
        Attraction savedAttraction = attractionRepository.save(attraction);
        // when
        Attraction result = attractionRepository.findById(savedAttraction.getId()).get();
        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Test get all by sort order and filter type functionality")
    void givenThreeSavedAttractionAndFilterAndSortOrder_whenFindBySortOrderAndType_thenReturnedOrderedList() {
        // given
        Pageable pageable = DataUtils.getPageableWithSortByName();
        Attraction attraction1 = DataUtils.getHomelParkTransient();
        Attraction attraction2 = DataUtils.getHomelPark2Transient();
        Attraction attraction3 = DataUtils.getHomelMuseumTransient();
        attractionRepository.save(attraction1);
        attractionRepository.save(attraction2);
        attractionRepository.save(attraction3);
        AttractionType attractionType = AttractionType.PARK;
        // when
        Page<Attraction> result = attractionRepository.findAllByType(attractionType, pageable);
        // then
        assertThat(result).isNotNull();
        assertThat(result.stream().allMatch(n -> n.getType().equals(attractionType))).isTrue();
    }

    @Test
    @DisplayName("Test get all by locality id functionality")
    void givenThreeSavedAttractionAndLocalityId_whenFindAllByLocalityId_thenReturnedList() {
        // given
        Pageable pageable = DataUtils.getPageable();
        Attraction attraction1 = DataUtils.getHomelParkTransient();
        Attraction attraction2 = DataUtils.getHomelPark2Transient();
        Attraction attraction3 = DataUtils.getHomelMuseumTransient();
        attractionRepository.save(attraction1);
        attractionRepository.save(attraction2);
        attractionRepository.save(attraction3);
        var id = 1L;
        // when
        Page<Attraction> result = attractionRepository.findAllByLocalityId(id, pageable);
        // then
        assertThat(result).isNotNull();
        assertThat(result.stream().allMatch(n -> n.getLocality().getId().equals(id))).isTrue();
    }

    @Test
    @DisplayName("Test get attraction by id functionality")
    void givenAttractionCreated_whenRemove_thenFindByIdIsNull() {
        // given
        Attraction attraction = DataUtils.getHomelParkTransient();
        Attraction savedAttraction = attractionRepository.save(attraction);
        // when
        attractionRepository.deleteById(savedAttraction.getId());
        // then
        Attraction obrainedAttraction = attractionRepository.findById(savedAttraction.getId()).orElse(null);
        assertThat(obrainedAttraction).isNull();
    }
}
