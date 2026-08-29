package com.railway_booking.railwaybooking.exception;

public class DuplicateStationException extends RuntimeException {
    public DuplicateStationException(String stationCode) {
        super("Station already exists with code: " + stationCode);
    }
}
