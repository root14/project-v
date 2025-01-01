package com.root14.projectv.service;

import com.root14.projectv.dto.NearDto;
import com.root14.projectv.model.Photo;
import com.root14.projectv.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final PhotoRepository photoRepository;

    @Autowired
    public LocationService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public GeoResults<Photo> getNearPhoto(NearDto nearDto) {
        Point location = new Point(nearDto.getLongitude(), nearDto.getLatitude());
        Distance distance = new Distance(nearDto.getRadius(), Metrics.KILOMETERS);
        PageRequest pageRequest=PageRequest.of(1,5);
        return photoRepository.findByPhotoNear(location, distance, pageRequest);
    }

}
