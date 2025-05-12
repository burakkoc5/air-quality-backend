# Air Quality Backend

## Project Description
This project provides a backend service for collecting, storing, and serving air quality data for cities in Turkey. It leverages Spring Boot, PostgreSQL with PostGIS for spatial data, and integrates with external APIs for real-time air quality information. The backend exposes RESTful endpoints for accessing city and air quality data, including geospatial queries.

## Features
- RESTful API for air quality data by city
- Geospatial queries and storage using PostGIS
- City boundaries and centroids stored as geometry (MultiPolygon)
- Fetch and store real-time air quality data (PM2.5, PM10, CO, SO2, NO2, O3, temperature, humidity)
- PostgreSQL database with spatial extensions
- DTO mapping and service layer abstraction
- Integration with OpenWeather API (API key required)

## Tech Stack
- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- PostgreSQL 15+ with PostGIS
- Hibernate Spatial & JTS Core (for geometry types)
- Lombok

## Database Schema
The main tables are:
- `cities`: Stores city boundaries (geometry), names, and identifiers
- `air_quality_data`: Stores air quality metrics for each city and timestamp
- `fetch_status`: Tracks the last fetch time for data updates


### Example Table Definitions
- `cities(gid, geom, il_prinx, name, mi_prinx)`
- `air_quality_data(id, city_id, pm2_5, pm10, co, so2, no2, o3, temperature, humidity, timestamp)`
- `fetch_status(id, last_fetch_time)`

## GIS & PostGIS Setup
This project requires a PostgreSQL database with the PostGIS extension enabled.

### Example PostGIS Setup Commands
```sql
-- Install PostGIS (if not already installed)
CREATE EXTENSION postgis;

-- Verify installation
SELECT PostGIS_Version();
```

### Example Connection String
```
spring.datasource.url=jdbc:postgresql://localhost:5432/air_quality_db?currentSchema=public
```

## Configuration
Edit `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/air_quality_db?currentSchema=public
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
openweather.api.key=YOUR_API_KEY
```

## API Endpoints
- `GET /api/cities/geojson` — List all cities with geometry as GeoJSON
- `GET /api/airquality/{cityId}` — Get latest air quality data for a city
- `POST /api/airquality/{cityId}` — Fetch and store new air quality data for a city

## Running the Project
1. Ensure PostgreSQL and PostGIS are installed and running
2. Create the database and enable PostGIS
3. Configure `application.properties` with your DB credentials and API key
4. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Notes on GIS Features
- The `City` entity uses a `MultiPolygon` geometry for city boundaries
- Geospatial queries and storage are handled via Hibernate Spatial and JTS
- PostGIS is required for geometry columns and spatial indexing
- Example: To import city boundaries (shapefile) into PostGIS, use:
  ```bash
  shp2pgsql -I -s 4326 cities.shp public.cities | psql -U postgres -d air_quality_db
  ```

## Author
Burak Koc