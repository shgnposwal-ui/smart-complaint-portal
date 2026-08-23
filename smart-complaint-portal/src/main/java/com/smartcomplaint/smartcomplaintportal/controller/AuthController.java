package com.smartcomplaint.smartcomplaintportal.controller;

import com.smartcomplaint.smartcomplaintportal.dto.LoginRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.LoginResponseDto;
import com.smartcomplaint.smartcomplaintportal.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDto login(
            @Valid @RequestBody LoginRequestDto loginRequestDto) {

        return authenticationService.login(loginRequestDto);
    }
}
