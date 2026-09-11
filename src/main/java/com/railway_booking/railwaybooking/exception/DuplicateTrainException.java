package com.railway_booking.railwaybooking.exception;

public class DuplicateTrainException extends RuntimeException {

    public DuplicateTrainException(String trainNumber) {
        super("Train number already exists: " + trainNumber);
    }
}