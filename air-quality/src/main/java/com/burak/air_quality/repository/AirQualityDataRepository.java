package com.burak.air_quality.repository;

import com.burak.air_quality.entity.AirQualityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirQualityDataRepository extends JpaRepository<AirQualityData, Long> {
    List<AirQualityData> findTop10ByCityGidOrderByTimestampDesc(Integer cityId);

}
