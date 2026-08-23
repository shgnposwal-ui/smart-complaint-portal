package com.smartcomplaint.smartcomplaintportal.service;

import com.smartcomplaint.smartcomplaintportal.dto.LoginRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.LoginResponseDto;

public interface AuthenticationService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

}
