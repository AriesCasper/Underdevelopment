package com.railway_booking.railwaybooking.dto;

public class TrainResponse {

    private long traindId;
    private String trainNumber;
    private String trainName;
    private String status;

    public long getTraindId() {
        return traindId;
    }

    public void setTraindId(long traindId) {
        this.traindId = traindId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
