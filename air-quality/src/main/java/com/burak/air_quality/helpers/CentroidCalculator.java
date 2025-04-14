package com.burak.air_quality.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;

public class CentroidCalculator {

    public static double[] getLatLonCentroid(String wktString) throws Exception {
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);

        Geometry geometry = reader.read(wktString);

        if (!geometry.isValid()) {
            geometry = geometry.buffer(0); // fix invalid geometry
        }

        Coordinate centroid = geometry.getCentroid().getCoordinate();

        // Return as [latitude, longitude]
        return new double[]{centroid.y, centroid.x};
    }
}

