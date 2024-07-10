package com.mannazo.user.dto;

import com.mannazo.user.client.auth.LoginRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserCreationRequestDTO {
    private LoginRequestDTO loginRequestDTO;
    private UserRequestDTO userRequestDTO;
}
