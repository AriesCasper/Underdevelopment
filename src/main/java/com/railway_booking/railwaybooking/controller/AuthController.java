package com.railway_booking.railwaybooking.controller;

import com.railway_booking.railwaybooking.dto.*;
import com.railway_booking.railwaybooking.service.AuthService;
import com.railway_booking.railwaybooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(
            AuthService authService,
            UserService userService) {

        this.authService = authService;
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse>  refresh(
            @RequestBody RefreshTokenRequest request) {

        return  new ResponseEntity<>(authService.refreshToken(request.getRefreshToken()), HttpStatus.CREATED) ;
    }




}
