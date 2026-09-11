package com.railway_booking.railwaybooking.repository;

import com.railway_booking.railwaybooking.entity.TrainStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainStopRepository extends JpaRepository<TrainStop, Long> {

    List<TrainStop> findByTrainTrainIdOrderByStopSequence(Long trainId);

    boolean existsByTrainTrainIdAndStopSequence(
            Long trainId,
            Integer stopSequence
    );
}