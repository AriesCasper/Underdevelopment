package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.TrainRequest;
import com.railway_booking.railwaybooking.dto.TrainResponse;
import com.railway_booking.railwaybooking.entity.Train;
import com.railway_booking.railwaybooking.exception.DuplicateTrainException;
import com.railway_booking.railwaybooking.exception.TrainNotFoundException;
import com.railway_booking.railwaybooking.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }
    @Transactional
    public TrainResponse createTrain(TrainRequest request) {

        if (trainRepository.existsByTrainNumber(request.getTrainNumber())) {
            throw new DuplicateTrainException(request.getTrainNumber());
        }

        Train train = new Train();

        train.setTrainNumber(request.getTrainNumber());
        train.setTrainName(request.getTrainName());
        train.setStatus("ACTIVE");

        Train savedTrain = trainRepository.save(train);

        return mapToResponse(savedTrain);
    }

    public List<TrainResponse> getAllTrains() {

        return trainRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TrainResponse getTrainById(Long id) {

        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new TrainNotFoundException(id));

        return mapToResponse(train);
    }
@Transactional
    public TrainResponse updateTrain(
            Long id,
            TrainRequest request
    ) {

        Train existingTrain = trainRepository.findById(id)
                .orElseThrow(() -> new TrainNotFoundException(id));

        if (trainRepository.existsByTrainNumberAndTrainIdNot(
                request.getTrainNumber(),
                id
        )) {
            throw new DuplicateTrainException(
                    request.getTrainNumber()
            );
        }

        existingTrain.setTrainNumber(request.getTrainNumber());
        existingTrain.setTrainName(request.getTrainName());

        Train updatedTrain = trainRepository.save(existingTrain);

        return mapToResponse(updatedTrain);
    }
@Transactional
    public void deleteTrain(Long id) {

        if (!trainRepository.existsById(id)) {
            throw new TrainNotFoundException(id);
        }

        trainRepository.deleteById(id);
    }

    private TrainResponse mapToResponse(Train train) {

        TrainResponse response = new TrainResponse();

        response.setTrainId(train.getTrainId());
        response.setTrainNumber(train.getTrainNumber());
        response.setTrainName(train.getTrainName());
        response.setStatus(train.getStatus());

        return response;
    }
}