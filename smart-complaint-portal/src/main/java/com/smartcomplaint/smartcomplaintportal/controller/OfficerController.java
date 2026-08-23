package com.smartcomplaint.smartcomplaintportal.controller;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintStatusUpdateDto;
import com.smartcomplaint.smartcomplaintportal.service.OfficerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.smartcomplaint.smartcomplaintportal.dto.OfficerDashboardDto;

import java.util.List;

@RestController
@RequestMapping("/api/officer")
@RequiredArgsConstructor
public class OfficerController {

    private final OfficerService officerService;
    @GetMapping("/dashboard")
    public OfficerDashboardDto getDashboard() {
        return officerService.getDashboard();
    }


    @GetMapping("/complaints")
    public List<ComplaintResponseDto> getAssignedComplaints() {
        return officerService.getAssignedComplaints();
    }

    @PutMapping("/complaints/{complaintNumber}/status")
    public ComplaintResponseDto updateComplaintStatus(
            @PathVariable String complaintNumber,
            @Valid @RequestBody ComplaintStatusUpdateDto request) {

        return officerService.updateComplaintStatus(
                complaintNumber,
                request
        );
    }
}