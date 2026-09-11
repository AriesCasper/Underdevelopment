package com.railway_booking.railwaybooking.controller;

import com.railway_booking.railwaybooking.dto.SeatResponse;
import com.railway_booking.railwaybooking.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/coaches")
public class AdminSeatController {

    private final SeatService seatService;

    public AdminSeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{coachId}/seats")
    public List<SeatResponse> getSeatsByCoach(
            @PathVariable Long coachId) {

        return seatService.getSeatsByCoach(coachId);
    }
}