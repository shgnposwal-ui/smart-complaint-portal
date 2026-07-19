package com.smartcomplaint.smartcomplaintportal.service;

import com.smartcomplaint.smartcomplaintportal.dto.UserRegistrationDto;
import com.smartcomplaint.smartcomplaintportal.dto.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);
}
