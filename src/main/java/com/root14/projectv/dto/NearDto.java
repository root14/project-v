package com.root14.projectv.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NearDto {
    private double latitude;
    private double longitude;
    private double radius;
}
