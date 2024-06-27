package com.mannazo.mannazo.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ExceptionDTO handleException(Exception e){
        return new ExceptionDTO(HttpStatus.BAD_REQUEST,e.getMessage());
    }
}
