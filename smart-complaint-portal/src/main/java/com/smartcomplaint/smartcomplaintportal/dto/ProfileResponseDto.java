package com.smartcomplaint.smartcomplaintportal.dto;

import com.smartcomplaint.smartcomplaintportal.entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private Role role;

    private Boolean isActive;

    private LocalDateTime createdAt;

}
