package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.ProfileResponseDto;
import com.smartcomplaint.smartcomplaintportal.entity.Role;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.service.OfficerManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerManagementServiceImpl implements OfficerManagementService {

    private final UserRepository userRepository;

    public OfficerManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<ProfileResponseDto> getAllOfficers() {

        List<User> officers =
                userRepository.findByRole(Role.OFFICER);

        return officers.stream()
                .map(user -> ProfileResponseDto.builder()
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
                        .build())
                .toList();
    }
}