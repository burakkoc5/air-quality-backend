package com.burak.air_quality.service;

import com.burak.air_quality.dto.CityDTO;
import com.burak.air_quality.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<CityDTO> getAllCities();
    CityDTO getCityById(Integer id);
    Optional<City> getCityByName(String name);
}
