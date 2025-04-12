package com.burak.air_quality.service;

import com.burak.air_quality.client.OpenWeatherMapClient;
import com.burak.air_quality.dto.AirQualityDataDTO;
import com.burak.air_quality.entity.AirQualityData;
import com.burak.air_quality.entity.City;
import com.burak.air_quality.mapper.AirQualityDataMapper;
import com.burak.air_quality.repository.AirQualityDataRepository;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirQualityDataServiceImpl implements AirQualityDataService {


    private AirQualityDataRepository airQualityDataRepository;
    private CityService cityService;
    private OpenWeatherMapClient openWeatherClient;

    private AirQualityDataMapper airQualityDataMapper;

    @Override
    public void fetchAndSaveDataForCity(Integer cityId) {
        City city = cityService.getCityById(cityId);

        Point centroid = city.getCentroid();
        double latitude = centroid.getY();
        double longitude = centroid.getX();

        AirQualityDataDTO apiData = openWeatherClient.fetchDataForCity(latitude,longitude);

        AirQualityData entity = new AirQualityData();
        entity.setCity(city);
        entity.setCo(apiData.getCo());
        entity.setO3(apiData.getO3());
        entity.setHumidity(apiData.getHumidity());
        entity.setPm10(apiData.getPm10());
        entity.setNo2(apiData.getNo2());
        entity.setPm25(apiData.getPm25());
        entity.setTemperature(apiData.getTemperature());
        entity.setTimestamp(LocalDateTime.now());

        airQualityDataRepository.save(entity);
    }

    @Override
    public List<AirQualityDataDTO> getLatestDataByCity(Long cityId) {
        List<AirQualityData> dataList = airQualityDataRepository.findTop10ByCityIdOrderByTimestampDesc(cityId);
        return dataList.stream().map(airQualityDataMapper::toDTO).collect(Collectors.toList());
    }
}
