package com.smartcomplaint.smartcomplaintportal.mapper;

import com.smartcomplaint.smartcomplaintportal.dto.UserRegistrationDto;
import com.smartcomplaint.smartcomplaintportal.dto.UserResponseDto;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.dto.ProfileResponseDto;

public class UserMapper {

    // Convert DTO to Entity
    public static User toEntity(UserRegistrationDto dto) {

        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .pincode(dto.getPincode())
                .role(dto.getRole())
                .build();
    }

    // Convert Entity to DTO
    public static UserRegistrationDto toDto(User user) {

        return UserRegistrationDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .city(user.getCity())
                .state(user.getState())
                .pincode(user.getPincode())
                .role(user.getRole())
                .build();
    }
    public static UserResponseDto toResponseDto(User user) {

        return UserResponseDto.builder()
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
                .build();
    }
    public static ProfileResponseDto toProfileResponseDto(User user) {

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
                .build();
    }
}