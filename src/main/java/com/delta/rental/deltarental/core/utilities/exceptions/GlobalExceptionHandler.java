package com.delta.rental.deltarental.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class}) // methodun içerisinde verilen türün ex. handler metodu olduğunu belirler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // return işlevinin cevabı hangi http statüsünde döndüreceğini belirler.
    public Map<String,String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new HashMap<>();

        for (FieldError error: exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(RuntimeException exception)
    {
        return exception.getMessage();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleError(Exception exception)
    {
        return "Bilinmedik hata";
    }
}
