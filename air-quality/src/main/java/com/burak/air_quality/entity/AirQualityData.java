package com.burak.air_quality.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "air_quality_data")
@Data
public class AirQualityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "gid", nullable = false)
    private City city;

    @Column(name = "pm2_5")
    private Float pm25;

    @Column(name = "pm10")
    private Float pm10;

    @Column(name = "co")
    private Float co;

    @Column(name = "so2")
    private Float so2;

    @Column(name = "no2")
    private Float no2;

    @Column(name = "o3")
    private Float o3;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "humidity")
    private Float humidity;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;

}
