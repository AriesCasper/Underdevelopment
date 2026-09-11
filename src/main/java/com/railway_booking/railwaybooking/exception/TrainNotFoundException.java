package com.railway_booking.railwaybooking.exception;

public class TrainNotFoundException extends RuntimeException {
    public TrainNotFoundException(Long id) {
        super("Train not found with id: " + id);
    }
}
