package ru.aston.springtask2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.springtask2.exception.LocalityAlreadyExistException;
import ru.aston.springtask2.exception.LocalityNotFoundException;
import ru.aston.springtask2.model.Locality;
import ru.aston.springtask2.repository.LocalityRepository;
import ru.aston.springtask2.service.LocalityService;

@Service
@RequiredArgsConstructor
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;

    /**
     * Поиск Locality по ID
     *
     * @param id ID для поиска
     * @return Возвращает объект Locality
     * @throws LocalityNotFoundException выбрасывает исключение, если объект не найден
     */
    @Override
    public Locality findById(Long id) {
        return localityRepository.findById(id).orElseThrow(() -> new LocalityNotFoundException(id));
    }

    /**
     * Создает новый объект Locality
     *
     * @param locality Объект для сохранения
     * @return Возвращает сохраненный объект
     * @throws LocalityAlreadyExistException возвращает исключение, если объект с такими названием и регионом уже существует
     */
    @Override
    public Locality create(Locality locality) {
        localityRepository.findFirstByNameAndRegion(locality.getName(), locality.getRegion())
                .ifPresent(value -> {
                    throw new LocalityAlreadyExistException(locality.getName(), locality.getRegion());
                });

        return localityRepository.save(locality);
    }
}
