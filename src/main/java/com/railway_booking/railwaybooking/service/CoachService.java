package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.CoachRequest;
import com.railway_booking.railwaybooking.dto.CoachResponse;
import com.railway_booking.railwaybooking.entity.Coach;
import com.railway_booking.railwaybooking.entity.Train;
import com.railway_booking.railwaybooking.exception.CoachNotFoundException;
import com.railway_booking.railwaybooking.exception.DuplicateCoachException;
import com.railway_booking.railwaybooking.exception.TrainNotFoundException;
import com.railway_booking.railwaybooking.repository.CoachRepository;
import com.railway_booking.railwaybooking.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.railway_booking.railwaybooking.entity.Seat;
import com.railway_booking.railwaybooking.repository.SeatRepository;
import java.util.List;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;
    private final SeatRepository seatRepository;

    public CoachService(
            CoachRepository coachRepository,
            TrainRepository trainRepository,
            SeatRepository seatRepository) {

        this.coachRepository = coachRepository;
        this.trainRepository = trainRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    public CoachResponse createCoach(CoachRequest request) {

        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() ->
                        new TrainNotFoundException(request.getTrainId()));

        if (coachRepository.existsByTrainTrainIdAndCoachNumber(
                request.getTrainId(),
                request.getCoachNumber())) {

            throw new DuplicateCoachException(
                    request.getCoachNumber(),
                    request.getTrainId());
        }

        Coach coach = new Coach();
        coach.setTrain(train);
        coach.setCoachNumber(request.getCoachNumber());
        coach.setCoachType(request.getCoachType());
        coach.setSeatCapacity(request.getSeatCapacity());
        coach.setStatus("ACTIVE");

        Coach savedCoach = coachRepository.save(coach);

        for (int i = 1; i <= savedCoach.getSeatCapacity(); i++) {

            Seat seat = new Seat();

            seat.setCoach(savedCoach);
            seat.setSeatNumber(i);
            seat.setSeatType("REGULAR");
            seat.setStatus("ACTIVE");

            seatRepository.save(seat);
        }

        return mapToResponse(savedCoach);
    }

    public List<CoachResponse> getAllCoaches() {

        return coachRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CoachResponse getCoachById(Long id) {

        Coach coach = coachRepository.findById(id)
                .orElseThrow(() ->
                        new CoachNotFoundException(id));

        return mapToResponse(coach);
    }

    @Transactional
    public CoachResponse updateCoach(Long id, CoachRequest request) {

        Coach existingCoach = coachRepository.findById(id)
                .orElseThrow(() ->
                        new CoachNotFoundException(id));

        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() ->
                        new TrainNotFoundException(request.getTrainId()));

        if (coachRepository.existsByTrainTrainIdAndCoachNumberAndCoachIdNot(
                request.getTrainId(),
                request.getCoachNumber(),
                id)) {

            throw new DuplicateCoachException(
                    request.getCoachNumber(),
                    request.getTrainId());
        }

        existingCoach.setTrain(train);
        existingCoach.setCoachNumber(request.getCoachNumber());
        existingCoach.setCoachType(request.getCoachType());
        existingCoach.setSeatCapacity(request.getSeatCapacity());

        Coach updatedCoach = coachRepository.save(existingCoach);

        return mapToResponse(updatedCoach);
    }

    @Transactional
    public void deleteCoach(Long id) {

        if (!coachRepository.existsById(id)) {
            throw new CoachNotFoundException(id);
        }

        coachRepository.deleteById(id);
    }

    private CoachResponse mapToResponse(Coach coach) {

        CoachResponse response = new CoachResponse();

        response.setCoachId(coach.getCoachId());
        response.setTrainId(coach.getTrain().getTrainId());
        response.setCoachNumber(coach.getCoachNumber());
        response.setCoachType(coach.getCoachType());
        response.setSeatCapacity(coach.getSeatCapacity());
        response.setStatus(coach.getStatus());

        return response;
    }
}