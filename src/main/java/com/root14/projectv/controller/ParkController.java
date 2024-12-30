package com.root14.projectv.controller;

import com.root14.projectv.dto.AddParkDto;
import com.root14.projectv.dto.GetParkDto;
import com.root14.projectv.dto.NearDto;
import com.root14.projectv.model.Photo;
import com.root14.projectv.service.LocationService;
import com.root14.projectv.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/park")
public class ParkController {

    private final ParkService parkService;
    private final LocationService locationService;

    @Autowired
    public ParkController(ParkService parkService, LocationService locationService) {
        this.parkService = parkService;
        this.locationService = locationService;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPark(AddParkDto addParkDto) {
        try {
            return parkService.addPark(addParkDto);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //decode base64, create img from these bytes
    @GetMapping(value = "/get")
    public ResponseEntity<?> getPark(@RequestBody GetParkDto getParkDto) {
        System.out.println("getPark");
        return parkService.getPark(getParkDto.getId());
    }

    @GetMapping("/findNear")
    public ResponseEntity<?> findNear(@RequestBody NearDto nearDto) {
        try {
            GeoResults<Photo> listGeoResult = locationService.getNearPhoto(nearDto);
            return new ResponseEntity<>(listGeoResult, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
