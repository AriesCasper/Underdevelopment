package com.railway_booking.railwaybooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TrainRequest {
    @NotBlank(message = "Train numer is required")
    @Size(min=1, max= 20, message = "Train number must be between 1 and 20")
    private String trainNumber;

    @NotBlank(message = "Train name is required")
    @Size(min =2, max=100, message = "Train name must be between 2 and 100 ")
    private String trainName;

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
}
