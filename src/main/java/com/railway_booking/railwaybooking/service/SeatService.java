package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.SeatResponse;
import com.railway_booking.railwaybooking.entity.Seat;
import com.railway_booking.railwaybooking.exception.CoachNotFoundException;
import com.railway_booking.railwaybooking.repository.CoachRepository;
import com.railway_booking.railwaybooking.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final CoachRepository coachRepository;

    public SeatService(
            SeatRepository seatRepository,
            CoachRepository coachRepository) {
        this.seatRepository = seatRepository;
        this.coachRepository = coachRepository;
    }

    public List<SeatResponse> getSeatsByCoach(Long coachId) {

        if (!coachRepository.existsById(coachId)) {
            throw new CoachNotFoundException(coachId);
        }

        return seatRepository.findByCoachCoachId(coachId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SeatResponse mapToResponse(Seat seat) {

        SeatResponse response = new SeatResponse();

        response.setSeatId(seat.getSeatId());
        response.setCoachId(seat.getCoach().getCoachId());
        response.setSeatNumber(seat.getSeatNumber());
        response.setSeatType(seat.getSeatType());
        response.setStatus(seat.getStatus());

        return response;
    }
}