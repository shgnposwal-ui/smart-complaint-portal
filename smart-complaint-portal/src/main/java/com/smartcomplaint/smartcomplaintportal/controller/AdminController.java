package com.smartcomplaint.smartcomplaintportal.controller;

import com.smartcomplaint.smartcomplaintportal.dto.AdminDashboardDto;
import com.smartcomplaint.smartcomplaintportal.dto.AdminDashboardResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.OfficerSummaryDto;
import com.smartcomplaint.smartcomplaintportal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.smartcomplaint.smartcomplaintportal.dto.CitizenSummaryDto;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/complaints")
    public List<ComplaintResponseDto> getAllComplaints() {
        return adminService.getAllComplaints();
    }

    @PutMapping("/complaints/{complaintNumber}/assign/{officerId}")
    public ComplaintResponseDto assignOfficer(
            @PathVariable String complaintNumber,
            @PathVariable Long officerId) {

        return adminService.assignOfficer(
                complaintNumber,
                officerId
        );
    }
    @GetMapping("/dashboard")
    public AdminDashboardDto getDashboardStatistics() {
        return adminService.getDashboardStatistics();
    }
    @GetMapping("/officers")
    public List<OfficerSummaryDto> getAllOfficers() {
        return adminService.getAllOfficers();
    }
    @GetMapping("/citizens")
    public List<CitizenSummaryDto> getAllCitizens() {
        return adminService.getAllCitizens();
    }
    @PutMapping("/officers/{id}/status")
    public OfficerSummaryDto updateOfficerStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return adminService.updateOfficerStatus(id, active);
    }
    @GetMapping
    public AdminDashboardResponseDto getDashboard() {
        return adminService.getDashboard();
    }

}
