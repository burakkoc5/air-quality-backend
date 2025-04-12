package com.burak.air_quality.controller;

import com.burak.air_quality.dto.CityDTO;
import com.burak.air_quality.service.CityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {


    private CityServiceImpl cityService;

    @GetMapping("/geojson")
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }
}
