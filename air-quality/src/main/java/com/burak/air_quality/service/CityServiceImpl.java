package com.burak.air_quality.service;

import com.burak.air_quality.dto.CityDTO;
import com.burak.air_quality.entity.City;
import com.burak.air_quality.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityDTO> getAllCities() {
        List<Object[]> rows = cityRepository.findAllCitiesWithGeoJson();

        return rows.stream().map(row -> new CityDTO(
                (Integer) row[0],
                (String) row[1],
                (String) row[2]
        )).toList();
    }

    @Override
    public CityDTO getCityById(Integer gid) {
        System.out.println();
        return cityRepository.findCityById(gid)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + gid));
    }

    @Override
    public Optional<City> getCityByName(String name) {
        return cityRepository.findByName(name);
    }
}
