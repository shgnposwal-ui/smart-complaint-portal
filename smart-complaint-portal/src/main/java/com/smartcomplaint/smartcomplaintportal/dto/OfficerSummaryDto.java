package com.smartcomplaint.smartcomplaintportal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficerSummaryDto {

    private Long id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String city;

    private String state;

    private Boolean isActive;
}
