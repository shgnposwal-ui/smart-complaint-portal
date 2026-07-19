package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.UserRegistrationDto;
import com.smartcomplaint.smartcomplaintportal.dto.UserResponseDto;
import com.smartcomplaint.smartcomplaintportal.entity.Role;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.mapper.UserMapper;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

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
}