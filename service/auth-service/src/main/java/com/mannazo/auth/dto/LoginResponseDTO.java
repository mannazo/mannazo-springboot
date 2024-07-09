package com.mannazo.auth.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponseDTO {
    private boolean firstTimeUser;
    private UUID userId;
    private String token;
}
