package com.brunadelmouro.ct_teste.controller;


import com.brunadelmouro.ct_teste.exceptions.ObjectNotFoundException;
import com.brunadelmouro.ct_teste.exceptions.StandardError;
import com.brunadelmouro.ct_teste.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StandardError>> beanValidation(MethodArgumentNotValidException e){

        List<StandardError> errors = e.getAllErrors().stream().map(
                x -> new StandardError(x.getDefaultMessage(),
                        DateUtils.convertSystemTimeMillisToString(System.currentTimeMillis()),
                        HttpStatus.BAD_REQUEST.value())
        ).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

}
