package ru.martha.lab2_3swagger_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmValidationException extends RuntimeException {
    public FilmValidationException(String message) {
        super(message);
    }
}