package com.mannazo.mannazo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExceptionDTO {
    private HttpStatus status;
    private String message;
}
