package com.smartcomplaint.smartcomplaintportal.mapper;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.entity.Complaint;

public class ComplaintMapper {

    private ComplaintMapper() {
    }

    public static ComplaintResponseDto toResponseDto(Complaint complaint) {

        return ComplaintResponseDto.builder()
                .id(complaint.getId())
                .complaintNumber(complaint.getComplaintNumber())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .status(complaint.getStatus())
                .priority(complaint.getPriority())
                .citizenName(
                        complaint.getCitizen().getFirstName() + " " +
                                complaint.getCitizen().getLastName()
                )
                .assignedOfficer(
                        complaint.getAssignedOfficer() == null
                                ? null
                                : complaint.getAssignedOfficer().getFirstName() + " "
                                + complaint.getAssignedOfficer().getLastName()
                )
                .address(complaint.getAddress())
                .city(complaint.getCity())
                .state(complaint.getState())
                .pincode(complaint.getPincode())
                .officerRemarks(complaint.getOfficerRemarks())   // <-- Add this line
                .createdAt(complaint.getCreatedAt())
                .build();
    }

    public static Complaint toEntity(ComplaintRequestDto dto) {

        return Complaint.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .priority(dto.getPriority())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .pincode(dto.getPincode())
                .build();
    }
}