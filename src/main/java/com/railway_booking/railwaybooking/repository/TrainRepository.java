package com.railway_booking.railwaybooking.repository;
import com.railway_booking.railwaybooking.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    boolean existsByTrainNumber(String trainNumber);

    boolean existsByTrainNumberAndTrainIdNot(
            String trainNumber,
            Long trainId
    );
}