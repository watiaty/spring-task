package ru.aston.springtask2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.aston.springtask2.exception.AttractionNotFoundException;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;
import ru.aston.springtask2.repository.AttractionRepository;
import ru.aston.springtask2.service.AttractionService;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    /**
     * Сохранает новую достопримечательность
     *
     * @param attraction Объект достопримечательности
     * @return Возвращает новую достопримечательность
     */
    @Override
    public Attraction createAttraction(Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    /**
     * Получает объект достопримечательности по ID
     *
     * @param id ID для поиска объекта
     * @return Возвращает объект достопримечательности
     */
    @Override
    public Attraction getById(Long id) {
        return findAttractionById(id);
    }

    /**
     * Получает страницу с отсортированными достопримечательностями с типом из перечисления AttractionType
     *
     * @param type     Тип достопримечательности
     * @param pageable Объект для пагинации и сортировки
     * @return Возвращает страницу с отфильтрованными и отсортированными достопримечательностями
     */
    @Override
    public Page<Attraction> getAllByType(AttractionType type, Pageable pageable) {
        return attractionRepository.findAllByType(type, pageable);
    }

    /**
     * Получает список достопримечательностей по наименованию Locality
     *
     * @param id ID Locality
     * @return Возвращает список достопримечательностей
     */
    @Override
    public Page<Attraction> getAllByLocalityId(Long id, Pageable pageable) {
        return attractionRepository.findAllByLocalityId(id, pageable);
    }

    /**
     * Обновляет описание достопримечательности
     *
     * @param id               ID объекта для изменения
     * @param shortDescription Новое описание
     * @return Возвращает измененную достопримечательность
     */
    @Override
    public Attraction updateDescription(Long id, String shortDescription) {
        Attraction attraction = findAttractionById(id);
        attraction.setDescription(shortDescription);
        return attractionRepository.save(attraction);
    }

    /**
     * Удалает достопримечательность
     *
     * @param id ID для удаления объекта
     */
    @Override
    public void deleteAttractionById(Long id) {
        Attraction attraction = findAttractionById(id);
        attractionRepository.delete(attraction);
    }

    /**
     * @param id ID объекта для поиска
     * @return Возвращает достопримечательность
     * @throws AttractionNotFoundException исключение, если достопримечательность не найдена
     */
    private Attraction findAttractionById(Long id) {
        return attractionRepository.findById(id)
                .orElseThrow(() -> new AttractionNotFoundException(id));
    }
}
