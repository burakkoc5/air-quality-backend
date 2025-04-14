package com.burak.air_quality.service;

import com.burak.air_quality.dto.AirQualityDataDTO;

import java.util.List;

public interface AirQualityDataService {

    void fetchAndSaveDataForCity(Integer cityId);

    List<AirQualityDataDTO> getLatestDataByCity(Integer cityId);
}
