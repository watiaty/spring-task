package ru.aston.springtask2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.springtask2.dtos.response.ErrorResponseDTO;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({AttractionNotFoundException.class, LocalityNotFoundException.class})
    public ErrorResponseDTO handleNotFoundException(RuntimeException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setStatus("404");
        errorResponseDto.setMessage(ex.getMessage());
        return errorResponseDto;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({LocalityAlreadyExistException.class})
    public ErrorResponseDTO handleConflictException(RuntimeException ex) {
        ErrorResponseDTO errorResponseDto = new ErrorResponseDTO();
        errorResponseDto.setStatus("409");
        errorResponseDto.setMessage(ex.getMessage());
        return errorResponseDto;
    }
}
