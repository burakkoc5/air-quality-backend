package com.burak.air_quality.client;

import com.burak.air_quality.dto.AirQualityDataDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
public class OpenWeatherMapClient {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public AirQualityDataDTO fetchDataForCity( Double latitude, Double longitude) {

        String airQualityUrl = String.format("http://api.openweathermap.org/data/2.5/air_pollution?lat=%f&lon=%f&appid=%s", latitude, longitude, apiKey);


        ResponseEntity<JsonNode> airQualityUrlResponse = restTemplate.getForEntity(airQualityUrl, JsonNode.class);

        if (airQualityUrlResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("OpenWeather API Error");
        }

        JsonNode airQualityResponseJson = airQualityUrlResponse.getBody();
        JsonNode components = airQualityResponseJson.path("list").get(0).path("components");


        String weatherUrl = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s", latitude, longitude, apiKey);
        ResponseEntity<JsonNode> weatherResponse = restTemplate.getForEntity(weatherUrl, JsonNode.class);

        if (weatherResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("OpenWeather API Error for Weather Data");
        }

        JsonNode weatherJson = weatherResponse.getBody();
        JsonNode main = weatherJson.path("main");


        double temperature = main.path("temp").asDouble();
        int humidity = main.path("humidity").asInt();
        double pm25 = components.path("pm2_5").asDouble();
        double pm10 = components.path("pm10").asDouble();
        double co = components.path("co").asDouble();
        double so2 = components.path("so2").asDouble();
        double no2 = components.path("no2").asDouble();
        double o3 = components.path("o3").asDouble();

        AirQualityDataDTO dto = new AirQualityDataDTO();
        dto.setTemperature((float) temperature);
        dto.setHumidity((float) humidity);
        dto.setPm25((float) pm25);
        dto.setPm10((float) pm10);
        dto.setCo((float) co);
        dto.setSo2((float) so2);
        dto.setNo2((float) no2);
        dto.setO3((float) o3);
        dto.setTimestamp(LocalDateTime.now());

        return dto;
    }
}
