package com.burak.air_quality.controller;

import com.burak.air_quality.dto.CityDTO;
import com.burak.air_quality.service.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins = "http://localhost:4200")

public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping("/geojson")
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }
}
