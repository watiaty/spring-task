package ru.aston.springtask2.exception;

public class AttractionNotFoundException extends RuntimeException {
    public AttractionNotFoundException(Long id) {
        super("Attraction with id " + id + " not found");
    }
}
