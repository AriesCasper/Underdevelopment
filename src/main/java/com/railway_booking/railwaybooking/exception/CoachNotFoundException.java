package com.railway_booking.railwaybooking.exception;

public class CoachNotFoundException extends RuntimeException {

    public CoachNotFoundException(Long id) {
        super("Coach not found with id: " + id);
    }
}