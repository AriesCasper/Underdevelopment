package com.railway_booking.railwaybooking.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // VALIDATION ERRORS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return errors;
    }


    // USER NOT FOUND
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFound(
            UserNotFoundException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("message", ex.getMessage());

        return error;
    }


    // STATION NOT FOUND
    @ExceptionHandler(StationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleStationNotFound(
            StationNotFoundException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error", "STATION_NOT_FOUND");
        error.put("message", ex.getMessage());

        return error;
    }


    // DUPLICATE STATION
    @ExceptionHandler(DuplicateStationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDuplicateStation(
            DuplicateStationException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error", "DUPLICATE_STATION");
        error.put("message", ex.getMessage());

        return error;
    }


    // DATABASE CONSTRAINT VIOLATION
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDuplicateData(
            DataIntegrityViolationException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("message", "Username or email already exists");

        return error;
    }
}
