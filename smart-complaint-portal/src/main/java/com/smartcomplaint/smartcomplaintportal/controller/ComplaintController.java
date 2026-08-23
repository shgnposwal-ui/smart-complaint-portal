package com.smartcomplaint.smartcomplaintportal.controller;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ComplaintResponseDto createComplaint(

            @Valid @RequestBody ComplaintRequestDto request) {

        return complaintService.createComplaint(request);
    }
    @GetMapping("/my")
    public List<ComplaintResponseDto> getMyComplaints() {

        return complaintService.getMyComplaints();
    }
    @GetMapping("/{complaintNumber}")
    public ComplaintResponseDto getComplaintByNumber(
            @PathVariable String complaintNumber) {

        return complaintService.getComplaintByNumber(complaintNumber);
    }
}
