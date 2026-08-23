package com.smartcomplaint.smartcomplaintportal.controller;

import com.smartcomplaint.smartcomplaintportal.dto.UserRegistrationDto;
import com.smartcomplaint.smartcomplaintportal.dto.UserResponseDto;
import com.smartcomplaint.smartcomplaintportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smartcomplaint.smartcomplaintportal.dto.ProfileResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.UpdateProfileDto;
import com.smartcomplaint.smartcomplaintportal.dto.ChangePasswordDto;

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
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDto> getProfile() {

        return ResponseEntity.ok(
                userService.getProfile()
        );

    }
    @PutMapping("/profile")
    public ResponseEntity<ProfileResponseDto> updateProfile(

            @Valid
            @RequestBody
            UpdateProfileDto request) {

        return ResponseEntity.ok(
                userService.updateProfile(request)
        );

    }
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(

            @Valid
            @RequestBody
            ChangePasswordDto request) {

        userService.changePassword(request);

        return ResponseEntity.ok(
                "Password changed successfully"
        );

    }
}