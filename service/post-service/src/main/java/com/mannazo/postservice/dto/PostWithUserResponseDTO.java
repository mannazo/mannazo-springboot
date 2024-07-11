package com.mannazo.postservice.dto;

import com.mannazo.postservice.client.dto.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostWithUserResponseDTO {
    private PostResponseDTO post;
    private UserResponseDTO user;
}
