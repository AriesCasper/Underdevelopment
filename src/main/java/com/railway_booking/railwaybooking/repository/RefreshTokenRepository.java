package com.railway_booking.railwaybooking.repository;

import com.railway_booking.railwaybooking.entity.RefreshToken;
import com.railway_booking.railwaybooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

    void deleteByToken(String token);

    Optional<RefreshToken> findByUser(User user);
}

