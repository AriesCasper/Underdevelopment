package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.StationRequest;
import com.railway_booking.railwaybooking.dto.StationResponse;
import com.railway_booking.railwaybooking.entity.Station;
import com.railway_booking.railwaybooking.exception.DuplicateStationException;
import com.railway_booking.railwaybooking.exception.StationNotFoundException;
import com.railway_booking.railwaybooking.repository.StationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Transactional
    public StationResponse createStation(StationRequest request) {

        if (stationRepository.existsByStationCode(request.getStationCode())) {
            throw new DuplicateStationException(request.getStationCode());
        }

        Station station = new Station();

        station.setStationCode(request.getStationCode());
        station.setStationName(request.getStationName());
        station.setCity(request.getCity());
        station.setState(request.getState());
        station.setStatus("ACTIVE");

        Station savedStation = stationRepository.save(station);

        return mapToResponse(savedStation);
    }

    public List<StationResponse> getAllStations() {

        return stationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StationResponse getStationById(Long id) {

        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));

        return mapToResponse(station);
    }

    @Transactional
    public StationResponse updateStation(
            Long id,
            StationRequest request
    ) {

        Station existingStation = stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));

        if (stationRepository.existsByStationCodeAndStationIdNot(
                request.getStationCode(),
                id
        )) {
            throw new DuplicateStationException(request.getStationCode());
        }

        existingStation.setStationCode(request.getStationCode());
        existingStation.setStationName(request.getStationName());
        existingStation.setCity(request.getCity());
        existingStation.setState(request.getState());

        Station updatedStation = stationRepository.save(existingStation);

        return mapToResponse(updatedStation);
    }

    @Transactional
    public void deleteStation(Long id) {

        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));

        station.setStatus("INACTIVE");

        stationRepository.save(station);
    }

    private StationResponse mapToResponse(Station station) {

        StationResponse response = new StationResponse();

        response.setStationId(station.getStationId());
        response.setStationCode(station.getStationCode());
        response.setStationName(station.getStationName());
        response.setCity(station.getCity());
        response.setState(station.getState());
        response.setStatus(station.getStatus());

        return response;
    }
}