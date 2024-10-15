package ru.aston.springtask2.service;

import org.springframework.stereotype.Service;
import ru.aston.springtask2.model.Locality;

@Service
public interface LocalityService {
    Locality findById(Long id);

    Locality create(Locality locality);
}
