package com.railway_booking.railwaybooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public class TrainStopRequest {

    // Which train this stop belongs to
    @NotNull(message = "Train ID is required")
    private Long trainId;

    // Which station this train stops at
    @NotNull(message = "Station ID is required")
    private Long stationId;

    // Position of the station in the train's route
    @NotNull(message = "Stop sequence is required")
    @Positive(message = "Stop sequence must be positive")
    private Integer stopSequence;

    // Train's arrival and departure time at this station
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    // Getters and setters
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(Integer stopSequence) {
        this.stopSequence = stopSequence;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}