package com.jitendra.myportfolio.exception;

import java.util.Date;

import com.jitendra.myportfolio.dto.ErrorResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return new ErrorResponseDTO(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage()
        );
    }

    @ExceptionHandler(ErrorSavingEntityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO errorSavingEntityExceptionHandler(ErrorSavingEntityException ex) {
        return new ErrorResponseDTO(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            ex.getMessage()
        );
    }
}
