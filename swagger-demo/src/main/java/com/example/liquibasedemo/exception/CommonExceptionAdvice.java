package com.example.liquibasedemo.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity onNotFound(NotFoundException cause){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cause.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity onIllegalArgument(IllegalArgumentException cause){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cause.getMessage());
    }
}
