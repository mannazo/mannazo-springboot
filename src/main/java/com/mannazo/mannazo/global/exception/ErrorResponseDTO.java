package com.mannazo.mannazo.global.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private Integer status;
    private String error;
    private String message;
    private String path;
}
