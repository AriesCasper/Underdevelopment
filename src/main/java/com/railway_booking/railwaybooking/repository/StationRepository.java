package com.railway_booking.railwaybooking.repository;

import com.railway_booking.railwaybooking.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

    boolean existsByStationCode(String stationCode);
    boolean existsByStationCodeAndStationIdNot(String stationCode, Long stationId);
}