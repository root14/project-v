package com.root14.projectv.repository;

import com.root14.projectv.model.Photo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
    Optional<Photo> findPhotoById(String id);

    Optional<List<Photo>> findPhotoByAuthorId(String authorId);

    GeoResults<Photo> findByPhotoNear(Point point, Distance distance, PageRequest pageRequest);
}
