package com.softserve.ukrainer.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.softserve.ukrainer.entity.ApiExceptionResponse;

@ControllerAdvice
public class UkrainerExceptionHandler {
    @ExceptionHandler({ UkrainerException.class })
    protected ResponseEntity<ApiExceptionResponse> handleApiException(UkrainerException ex) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(ex.getCode(), ex.getMessage(), Instant.now());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
