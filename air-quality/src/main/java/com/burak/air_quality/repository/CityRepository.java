package com.burak.air_quality.repository;

import com.burak.air_quality.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByName(String name);

    @Query(
            value = "SELECT gid, name, ST_AsGeoJSON(geom) AS geojson FROM cities",
            nativeQuery = true
    )
    List<Object[]> findAllCitiesWithGeoJson();


}
