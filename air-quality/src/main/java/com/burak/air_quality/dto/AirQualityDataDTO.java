package com.burak.air_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirQualityDataDTO {

    private Float pm25;
    private Float pm10;
    private Float co;
    private Float so2;
    private Float no2;
    private Float o3;
    private Float temperature;
    private Float humidity;
    private LocalDateTime timestamp;
}
