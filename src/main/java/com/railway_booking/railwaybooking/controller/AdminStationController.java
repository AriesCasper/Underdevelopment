package com.railway_booking.railwaybooking.controller;

import com.railway_booking.railwaybooking.dto.StationRequest;
import com.railway_booking.railwaybooking.dto.StationResponse;
import com.railway_booking.railwaybooking.service.StationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/stations")
public class AdminStationController {

    private final StationService stationService;

    public AdminStationController(StationService stationService) {
        this.stationService = stationService;
    }

    // CREATE STATION
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StationResponse createStation(
            @Valid @RequestBody StationRequest request) {

        return stationService.createStation(request);
    }

    // GET ALL STATIONS
    @GetMapping
    public List<StationResponse> getAllStations() {

        return stationService.getAllStations();
    }

    // GET STATION BY ID
    @GetMapping("/{id}")
    public StationResponse getStationById(
            @PathVariable Long id) {

        return stationService.getStationById(id);
    }

    // UPDATE STATION
    @PutMapping("/{id}")
    public StationResponse updateStation(
            @PathVariable Long id,
            @Valid @RequestBody StationRequest request) {

        return stationService.updateStation(id, request);
    }

    // DELETE STATION
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStation(
            @PathVariable Long id) {

        stationService.deleteStation(id);
    }
}