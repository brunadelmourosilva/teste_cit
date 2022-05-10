package com.brunadelmouro.ct_teste.controller;


import com.brunadelmouro.ct_teste.exceptions.ObjectNotFoundException;
import com.brunadelmouro.ct_teste.exceptions.StandardError;
import com.brunadelmouro.ct_teste.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
        return ResponseEntity.ok(
                new StandardError(e.getMessage(),
                        DateUtils.convertSystemTimeMillisToString(System.currentTimeMillis()),
                        HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e){
        return ResponseEntity.ok(
                new StandardError(e.getMessage(),
                        DateUtils.convertSystemTimeMillisToString(System.currentTimeMillis()),
                        HttpStatus.NOT_FOUND.value()));
    }

}
