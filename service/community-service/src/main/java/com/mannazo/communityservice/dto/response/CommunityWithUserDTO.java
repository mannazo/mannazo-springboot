package com.mannazo.communityservice.dto.response;

import com.mannazo.communityservice.client.dto.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityWithUserDTO {
    private CommunityResponseDTO community;
    private UserResponseDTO user;
}
