package com.smartcomplaint.smartcomplaintportal.service;

import com.smartcomplaint.smartcomplaintportal.dto.AdminDashboardDto;
import com.smartcomplaint.smartcomplaintportal.dto.AdminDashboardResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.CitizenSummaryDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.OfficerSummaryDto;

import java.util.List;

public interface AdminService {

    List<ComplaintResponseDto> getAllComplaints();

    ComplaintResponseDto assignOfficer(
            String complaintNumber,
            Long officerId
    );

    AdminDashboardDto getDashboardStatistics();

    List<OfficerSummaryDto> getAllOfficers();

    List<CitizenSummaryDto> getAllCitizens();
    OfficerSummaryDto updateOfficerStatus(Long id, boolean active);

    AdminDashboardResponseDto getDashboard();

}
