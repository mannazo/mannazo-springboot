package com.mannazo.mannazo.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
public class UserRegistrationResponse {
    private UUID userId;
    private String jwtToken;
}
