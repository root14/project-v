package com.root14.projectv.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AddParkDto {
    private String userId;
    private String content;
    private MultipartFile image;
    private Double longitude;
    private Double latitude;
}
