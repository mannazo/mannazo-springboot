package com.mannazo.communityservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class ImageResponseDTO {
    private UUID imageId;
    private String filePath;
    private UUID communityId;
}
