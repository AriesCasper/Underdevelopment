package com.railway_booking.railwaybooking.exception;

public class DuplicateCoachException extends RuntimeException {

    public DuplicateCoachException(String coachNumber, Long trainId) {
        super("Coach " + coachNumber + " already exists for train: " + trainId);
    }
}