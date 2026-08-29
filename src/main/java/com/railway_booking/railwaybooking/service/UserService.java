package com.railway_booking.railwaybooking.service;

import com.railway_booking.railwaybooking.dto.UserRequest;
import com.railway_booking.railwaybooking.dto.UserResponse;
import com.railway_booking.railwaybooking.entity.User;
import com.railway_booking.railwaybooking.exception.UserNotFoundException;
import com.railway_booking.railwaybooking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE
    public UserResponse createUser(UserRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // Hash password before storing it
        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        user.setPhoneNumber(request.getPhoneNumber());
        user.setStatus("ACTIVE");
        user.setRole("USER");

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    // GET ALL
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET BY ID
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapToResponse(user);
    }

    // UPDATE
    public UserResponse updateUser(Long id, UserRequest request) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setUsername(request.getUsername());
        existingUser.setFullName(request.getFullName());
        existingUser.setEmail(request.getEmail());


        existingUser.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        existingUser.setPhoneNumber(request.getPhoneNumber());

        User updatedUser = userRepository.save(existingUser);

        return mapToResponse(updatedUser);
    }

    // DELETE
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    // ENTITY → RESPONSE DTO
    private UserResponse mapToResponse(User user) {

        UserResponse response = new UserResponse();

        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setStatus(user.getStatus());
        response.setRole(user.getRole());

        return response;
    }
}