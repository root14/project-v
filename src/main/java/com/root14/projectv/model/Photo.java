package com.root14.projectv.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
@Builder
@Data
public class Photo {
    @Id
    private String id;
    private String authorId;
    private String content;
    private Binary photo;
    private GeoJsonPoint location;
}
