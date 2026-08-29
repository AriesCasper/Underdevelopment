package com.railway_booking.railwaybooking.controller;

import com.railway_booking.railwaybooking.dto.LoginRequest;
import com.railway_booking.railwaybooking.dto.LoginResponse;
import com.railway_booking.railwaybooking.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}
