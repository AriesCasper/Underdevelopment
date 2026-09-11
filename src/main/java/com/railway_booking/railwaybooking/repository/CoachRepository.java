package com.railway_booking.railwaybooking.repository;

import com.railway_booking.railwaybooking.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    boolean existsByTrainTrainIdAndCoachNumber(Long trainId, String coachNumber);

    boolean existsByTrainTrainIdAndCoachNumberAndCoachIdNot(
            Long trainId,
            String coachNumber,
            Long coachId
    );
}