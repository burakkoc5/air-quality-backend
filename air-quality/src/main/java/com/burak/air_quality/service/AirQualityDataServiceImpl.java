package com.burak.air_quality.service;

import com.burak.air_quality.client.OpenWeatherMapClient;
import com.burak.air_quality.dto.AirQualityDataDTO;
import com.burak.air_quality.dto.CityDTO;
import com.burak.air_quality.entity.AirQualityData;
import com.burak.air_quality.entity.City;
import com.burak.air_quality.entity.FetchStatus;
import com.burak.air_quality.helpers.CentroidCalculator;
import com.burak.air_quality.mapper.AirQualityDataMapper;
import com.burak.air_quality.repository.AirQualityDataRepository;
import com.burak.air_quality.repository.CityRepository;
import com.burak.air_quality.repository.FetchStatusRepository;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirQualityDataServiceImpl implements AirQualityDataService {

    @Autowired
    private AirQualityDataRepository airQualityDataRepository;
    @Autowired
    private CityService cityService;
    @Autowired
    private OpenWeatherMapClient openWeatherClient;
    @Autowired
    private AirQualityDataMapper airQualityDataMapper;
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FetchStatusRepository fetchStatusRepository;

    @Override
    public void fetchAndSaveDataForCity(Integer cityId) {

        if (isDataFetchedLast24hr()){
            return;
        }

        CityDTO city = cityService.getCityById(cityId);



        //Point centroid = city.getCentroid();
        double[] latLongPair = null;
        try {
            latLongPair = CentroidCalculator.getLatLonCentroid(city.getGeometryGeoJson());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        AirQualityDataDTO apiData = openWeatherClient.fetchDataForCity(latLongPair[0],latLongPair[1]);

        AirQualityData entity = new AirQualityData();
        entity.setCity(new City(city.getGid(), null,null,city.getName(),null));
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
    public List<AirQualityDataDTO> getLatestDataByCity(Integer cityId) {
        List<AirQualityData> dataList = airQualityDataRepository.findTop10ByCityGidOrderByTimestampDesc(cityId);
        return dataList.stream().map(airQualityDataMapper::toDTO).collect(Collectors.toList());
    }

    private boolean isDataFetchedLast24hr() {

        System.out.println("Mwthod Called");
        FetchStatus status = fetchStatusRepository.findById(1L).orElseGet(() -> {
            FetchStatus newStatus = new FetchStatus();
            newStatus.setLastFetchTime(LocalDateTime.MIN); // force fetch first time
            System.out.println("Returns true for 24hr difference");
            return newStatus;
        });

        if (status.getLastFetchTime().isAfter(LocalDateTime.now().minusHours(24))) {
            System.out.println("Data already fetched in the last 24 hours. Skipping.");
            System.out.println("Returns false for 24hr difference");

            return true;
        }



        return false;
    }
}
