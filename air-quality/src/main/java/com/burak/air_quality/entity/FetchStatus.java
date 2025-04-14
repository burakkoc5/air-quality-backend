package com.burak.air_quality.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchStatus {

    @Id
    private Long id = 1L;

    @Column(name = "last_fetch_time")
    private LocalDateTime lastFetchTime;

    // Getters and setters
}