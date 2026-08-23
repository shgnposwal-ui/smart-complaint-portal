package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.LoginRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.LoginResponseDto;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceNotFoundException;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.security.CustomUserDetails;
import com.smartcomplaint.smartcomplaintportal.service.AuthenticationService;
import com.smartcomplaint.smartcomplaintportal.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        CustomUserDetails userDetails = new CustomUserDetails(user);



        String token = jwtService.generateToken(userDetails);

        return LoginResponseDto.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}