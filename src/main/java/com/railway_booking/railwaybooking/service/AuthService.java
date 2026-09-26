package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.LoginRequest;
import com.railway_booking.railwaybooking.dto.LoginResponse;
import com.railway_booking.railwaybooking.entity.RefreshToken;
import com.railway_booking.railwaybooking.entity.User;
import com.railway_booking.railwaybooking.exception.InvalidRefreshTokenException;
import com.railway_booking.railwaybooking.repository.RefreshTokenRepository;
import com.railway_booking.railwaybooking.repository.UserRepository;
import com.railway_booking.railwaybooking.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService, RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new RuntimeException("Invalid username or password");
        }

        // Generate access token
        String accessToken = jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );

        // Create/reuse ONE refresh token for this user
        RefreshToken refreshToken =
                refreshTokenService.createOrUpdateRefreshToken(user);

        return new LoginResponse(
                accessToken,
                refreshToken.getToken(),
                "Bearer"
        );
    }

    public LoginResponse refreshToken(String token) {

        RefreshToken oldRefreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(InvalidRefreshTokenException::new
                );

        refreshTokenService.verifyExpiration(oldRefreshToken);

        User user = oldRefreshToken.getUser();

        // Invalidate old refresh token A
        refreshTokenRepository.delete(oldRefreshToken);


        // Generate new access token
        String accessToken = jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );

        // Generate new refresh token B
        RefreshToken newRefreshToken =
                refreshTokenService.createOrUpdateRefreshToken(user);

        return new LoginResponse(
                accessToken,
                newRefreshToken.getToken(),
                "Bearer"
        );
    }

}