package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.TrainStopRequest;
import com.railway_booking.railwaybooking.dto.TrainStopResponse;
import com.railway_booking.railwaybooking.repository.StationRepository;
import com.railway_booking.railwaybooking.repository.TrainRepository;
import com.railway_booking.railwaybooking.repository.TrainStopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainStopService {
    private final TrainStopRepository trainStopRepository;
    private  final TrainRepository trainRepository;
    private  final StationRepository stationRepository;

    public TrainStopService(
            TrainStopRepository trainStopRepository,
            TrainRepository trainRepository,
            StationRepository stationRepository
    ){
        this.trainStopRepository = trainStopRepository;
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
    }

  @Transactional
  public TrainStopResponse createTrainStop(TrainStopRequest request){
        
  }
}
