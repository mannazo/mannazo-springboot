package com.mannazo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Setter
public class LoginDTO {

    private UUID userId;
    private String token;
}
