package com.railway_booking.railwaybooking.dto;

import java.time.LocalTime;

public class TrainStopResponse {

    // ID of this train-stop record
    private Long trainStopId;

    // Basic train information
    private Long trainId;
    private String trainNumber;
    private String trainName;

    // Basic station information
    private Long stationId;
    private String stationCode;
    private String stationName;

    // Position in the train route
    private Integer stopSequence;

    // Timing at this station
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    // Getters and setters

    public Long getTrainStopId() {
        return trainStopId;
    }

    public void setTrainStopId(Long trainStopId) {
        this.trainStopId = trainStopId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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