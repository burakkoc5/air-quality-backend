package com.burak.air_quality.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "cities", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;

    @Column(name = "geom")
    private MultiPolygon geom;

    @Column(name = "il_prinx")
    private Double ilPrinx;

    @Column(name = "name")
    private String name;

    @Column(name = "mi_prinx")
    private Integer miPrinx;

    public Point getCentroid() {
        return geom.getCentroid();
    }
}
