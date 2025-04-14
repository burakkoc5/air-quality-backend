package com.burak.air_quality.repository;

import com.burak.air_quality.entity.FetchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FetchStatusRepository extends JpaRepository<FetchStatus, Long> {

}