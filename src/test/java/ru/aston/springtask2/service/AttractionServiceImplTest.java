package ru.aston.springtask2.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;
import ru.aston.springtask2.exception.AttractionNotFoundException;
import ru.aston.springtask2.repository.AttractionRepository;
import ru.aston.springtask2.service.impl.AttractionServiceImpl;
import ru.aston.springtask2.util.DataUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AttractionServiceImplTest {
    @Mock
    private AttractionRepository attractionRepository;

    @InjectMocks
    private AttractionServiceImpl attractionService;

    @Test
    @DisplayName("Test save attraction functionality")
    void givenAttractionToSave_whenSave_thenRepositoryIsCalled() {
        // given
        Attraction attractionToSave = DataUtils.getHomelParkTransient();
        BDDMockito.given(attractionRepository.save(any(Attraction.class)))
                .willReturn(DataUtils.getHomelParkPersisted());
        // when
        Attraction savedAttraction = attractionService.createAttraction(attractionToSave);
        // then
        assertThat(savedAttraction).isNotNull();
    }

    @Test
    @DisplayName("Test find attraction by id functionality")
    void givenId_whenGetById_thenRepositoryIsCalled() {
        // given
        Long id = 1L;
        BDDMockito.given(attractionRepository.findById(any(Long.class)))
                .willReturn(Optional.ofNullable(DataUtils.getHomelParkPersisted()));
        // when
        Attraction foundAttraction = attractionService.getById(id);
        // then
        assertThat(foundAttraction).isNotNull();
    }

    @Test
    @DisplayName("Test find attraction by id functionality")
    void givenId_whenGetById_thenExceptionIsThrow() {
        // given
        Long id = 1L;
        BDDMockito.given(attractionRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        // when and then
        assertThrows(AttractionNotFoundException.class, () -> attractionService.getById(id));
    }

    @Test
    @DisplayName("Test find all attractions by type and sort order functionality")
    void givenThreeAttraction_whenGetAll_thenListIsReturned() {
        // given
        Pageable pageable = DataUtils.getPageableWithSortByName();
        Attraction attraction1 = DataUtils.getHomelParkTransient();
        Attraction attraction2 = DataUtils.getHomelPark2Transient();
        Attraction attraction3 = DataUtils.getHomelMuseumTransient();
        Page<Attraction> attractions = new PageImpl<>(List.of(attraction1, attraction2, attraction3));
        BDDMockito.given(attractionRepository.findAllByType(AttractionType.PARK, pageable))
                .willReturn(attractions);
        // when
        Page<Attraction> obtainedAttractions = attractionService.getAllByType(AttractionType.PARK, pageable);
        // then
        assertThat(obtainedAttractions.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Test find all attractions by locality functionality")
    void givenThreeAttraction_whenGetAllByLocality_thenListIsReturned() {
        // given
        var id = 1L;
        Pageable pageable = DataUtils.getPageable();
        Attraction attraction1 = DataUtils.getHomelParkTransient();
        Attraction attraction2 = DataUtils.getHomelPark2Transient();
        Attraction attraction3 = DataUtils.getHomelMuseumTransient();
        Page<Attraction> attractions = new PageImpl<>(List.of(attraction1, attraction2, attraction3));
        BDDMockito.given(attractionRepository.findAllByLocalityId(id, pageable))
                .willReturn(attractions);
        // when
        Page<Attraction> obtainedAttractions = attractionService.getAllByLocalityId(id, pageable);
        // then
        assertThat(obtainedAttractions.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Test update description of attraction functionality")
    void givenId_whenUpdateDescription_thenThrowException() {
        // given
        Long id = 1L;
        BDDMockito.given(attractionRepository.findById(id))
                .willReturn(Optional.empty());
        // when and then
        assertThrows(AttractionNotFoundException.class, () ->  attractionService.updateDescription(id, "description"));
    }

    @Test
    @DisplayName("Test update description of attraction functionality")
    void givenAttraction_whenUpdateDescription_thenDescriptionIsUpdated() {
        // given
        Attraction attraction = DataUtils.getHomelParkPersisted();
        String newDescription = "new description";
        BDDMockito.given(attractionRepository.findById(attraction.getId()))
                .willReturn(Optional.of(attraction));
        attraction.setDescription(newDescription);
        BDDMockito.given(attractionRepository.save(any(Attraction.class)))
                .willReturn(attraction);
        // when
        attractionService.updateDescription(attraction.getId(), newDescription);
        // then
        verify(attractionRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test remove attraction functionality")
    void givenAttraction_whenRemove_thenRepositoryIsCalled() {
        // given
        Attraction attraction = DataUtils.getHomelParkPersisted();
        BDDMockito.given(attractionRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(attraction));
        // when
        attractionService.deleteAttractionById(attraction.getId());
        // then
        verify(attractionRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("Test remove attraction functionality")
    void givenAttraction_whenRemove_thenThrowsException() {
        // given
        Attraction attraction = DataUtils.getHomelParkPersisted();
        BDDMockito.given(attractionRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        // when
        assertThrows(AttractionNotFoundException.class, () ->  attractionService.deleteAttractionById(attraction.getId()));
        // then
        verify(attractionRepository, never()).delete(any());
    }
}
