package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.*;
import com.smartcomplaint.smartcomplaintportal.entity.Role;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.mapper.UserMapper;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceAlreadyExistsException;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationDto userRegistrationDto) {

        // Check if email already exists
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists!");
        }

        // Convert DTO to Entity
        User user = UserMapper.toEntity(userRegistrationDto);

        // Encrypt Password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role if not provided
        if (user.getRole() == null) {
            user.setRole(Role.CITIZEN);
        }

        // Save user
        User savedUser = userRepository.save(user);

        // Convert Entity back to DTO
        return UserMapper.toResponseDto(savedUser);
    }
    @Override
    public ProfileResponseDto getProfile() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return ProfileResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .city(user.getCity())
                .state(user.getState())
                .pincode(user.getPincode())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public ProfileResponseDto updateProfile(UpdateProfileDto request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setPincode(request.getPincode());

        User updatedUser = userRepository.save(user);

        return UserMapper.toProfileResponseDto(updatedUser);
    }
    @Override
    public void changePassword(ChangePasswordDto request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Check current password
        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "Current password is incorrect"
            );
        }

        // Check new password and confirm password
        if (!request.getNewPassword().equals(
                request.getConfirmPassword())) {

            throw new IllegalArgumentException(
                    "New password and Confirm password do not match"
            );
        }

        // Update password
        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);
    }
}