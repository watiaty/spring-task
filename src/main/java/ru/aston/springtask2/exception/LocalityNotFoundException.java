package ru.aston.springtask2.exception;

public class LocalityNotFoundException extends RuntimeException {
    public LocalityNotFoundException(Long id) {
        super("Locality with id " + id + " not found");
    }
}
