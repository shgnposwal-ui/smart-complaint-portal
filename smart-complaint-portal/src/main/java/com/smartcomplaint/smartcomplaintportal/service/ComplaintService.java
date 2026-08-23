package com.smartcomplaint.smartcomplaintportal.service;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;

import java.util.List;

public interface ComplaintService {

    ComplaintResponseDto createComplaint(ComplaintRequestDto request);

    List<ComplaintResponseDto> getMyComplaints();

    ComplaintResponseDto getComplaintByNumber(String complaintNumber);
    List<ComplaintResponseDto> getAllComplaints();

    ComplaintResponseDto assignOfficer(
            String complaintNumber,
            Long officerId
    );
}