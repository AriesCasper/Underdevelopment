package com.railway_booking.railwaybooking.controller;

import com.railway_booking.railwaybooking.dto.CoachRequest;
import com.railway_booking.railwaybooking.dto.CoachResponse;
import com.railway_booking.railwaybooking.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/coaches")
public class AdminCoachController {

    private final CoachService coachService;

    public AdminCoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoachResponse createCoach(
            @Valid @RequestBody CoachRequest request) {
        return coachService.createCoach(request);
    }

    @GetMapping
    public List<CoachResponse> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @GetMapping("/{id}")
    public CoachResponse getCoachById(@PathVariable Long id) {
        return coachService.getCoachById(id);
    }

    @PutMapping("/{id}")
    public CoachResponse updateCoach(
            @PathVariable Long id,
            @Valid @RequestBody CoachRequest request) {
        return coachService.updateCoach(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
    }
}