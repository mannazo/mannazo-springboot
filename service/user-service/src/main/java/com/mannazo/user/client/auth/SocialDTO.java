package com.mannazo.user.client.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SocialDTO {
    private UUID userid;
    private String sosialId;
}
