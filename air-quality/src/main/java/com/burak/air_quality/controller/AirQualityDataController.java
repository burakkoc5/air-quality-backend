package com.burak.air_quality.controller;

import com.burak.air_quality.dto.AirQualityDataDTO;
import com.burak.air_quality.service.AirQualityDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airquality")
@CrossOrigin(origins = "http://localhost:4200")
public class AirQualityDataController {

    @Autowired
    private AirQualityDataServiceImpl airQualityDataService;

    @PostMapping("/{cityId}")
    public ResponseEntity<?> fetchData(@PathVariable Integer cityId) {
        airQualityDataService.fetchAndSaveDataForCity(cityId);
        return ResponseEntity.ok("Veri başarıyla çekildi.");
    }

    @GetMapping("/{cityId}")
    public List<AirQualityDataDTO> getLatestData(@PathVariable Integer cityId) {
        return airQualityDataService.getLatestDataByCity(cityId);
    }
}
