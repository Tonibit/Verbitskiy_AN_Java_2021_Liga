package com.verbitskiy.springboot_mvc.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity onNotFound(NotFoundException cause) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cause.getMessage());
    }
}
