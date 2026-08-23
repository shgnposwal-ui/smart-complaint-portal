package com.smartcomplaint.smartcomplaintportal.service;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintStatusUpdateDto;
import com.smartcomplaint.smartcomplaintportal.dto.OfficerDashboardDto;

import java.util.List;

public interface OfficerService {

    OfficerDashboardDto getDashboard();


    List<ComplaintResponseDto> getAssignedComplaints();

    ComplaintResponseDto updateComplaintStatus(
            String complaintNumber,
            ComplaintStatusUpdateDto request
    );
}
