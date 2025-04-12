package com.burak.air_quality.mapper;

import com.burak.air_quality.dto.AirQualityDataDTO;
import com.burak.air_quality.entity.AirQualityData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AirQualityDataMapper {
    public AirQualityDataDTO toDTO(AirQualityData entity) {
        AirQualityDataDTO dto = new AirQualityDataDTO();
        dto.setPm25(entity.getPm25());
        dto.setCo(entity.getCo());
        dto.setO3(entity.getO3());
        dto.setHumidity(entity.getHumidity());
        dto.setPm10(entity.getPm10());
        dto.setNo2(entity.getNo2());
        dto.setTemperature(entity.getTemperature());
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }
}