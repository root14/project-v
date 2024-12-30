package com.root14.projectv.service;

import com.root14.projectv.dto.GetParkDto;
import com.root14.projectv.model.Photo;
import com.root14.projectv.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ParkService {

    private final PhotoRepository photoRepository;

    @Autowired
    public ParkService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public ResponseEntity<Object> addPark(String longitude, String latitude, String userId, String content, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        Photo photo = Photo.builder()
                .authorId(userId)
                .photo(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        photoRepository.save(photo);
        return ResponseEntity.ok().body(photo.getId());
    }

    public ResponseEntity<Object> getPark(String parkId) {
        try {
            Optional<Photo> photo = photoRepository.findPhotoById(parkId);
            if (photo.isPresent()) {
                Photo p = photo.get();
                String string = Base64.getEncoder().encodeToString(p.getPhoto().getData());
                return ResponseEntity.ok().body(string);
            } else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
