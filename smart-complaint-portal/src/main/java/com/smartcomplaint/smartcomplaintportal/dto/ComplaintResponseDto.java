package com.smartcomplaint.smartcomplaintportal.dto;

import com.smartcomplaint.smartcomplaintportal.entity.ComplaintCategory;
import com.smartcomplaint.smartcomplaintportal.entity.ComplaintStatus;
import com.smartcomplaint.smartcomplaintportal.entity.Priority;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintResponseDto {

    private Long id;

    private String complaintNumber;

    private String title;

    private String description;

    private ComplaintCategory category;

    private ComplaintStatus status;

    private Priority priority;

    private String citizenName;

    private String assignedOfficer;

    private String address;

    private String city;

    private String state;

    private String pincode;
    private String officerRemarks;

    private LocalDateTime createdAt;
}
