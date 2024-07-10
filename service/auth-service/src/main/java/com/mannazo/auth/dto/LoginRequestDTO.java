package com.mannazo.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDTO {
    private String socialId;
    private String secret;
    private String provider;
}