package com.smartcomplaint.smartcomplaintportal.service;

import com.smartcomplaint.smartcomplaintportal.dto.*;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(UpdateProfileDto request);

    void changePassword(ChangePasswordDto request);
}

