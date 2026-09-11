package com.railway_booking.railwaybooking.repository;

import com.railway_booking.railwaybooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByCoachCoachId(Long coachId);

    boolean existsByCoachCoachIdAndSeatNumber(
            Long coachId,
            Integer seatNumber
    );
}