package com.smartcomplaint.smartcomplaintportal.controller;

import com.smartcomplaint.smartcomplaintportal.dto.UserRegistrationDto;
import com.smartcomplaint.smartcomplaintportal.dto.UserResponseDto;
import com.smartcomplaint.smartcomplaintportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(
            @Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        UserResponseDto response = userService.registerUser(userRegistrationDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}