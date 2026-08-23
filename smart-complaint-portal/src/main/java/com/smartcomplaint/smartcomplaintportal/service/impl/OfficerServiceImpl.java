package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintStatusUpdateDto;
import com.smartcomplaint.smartcomplaintportal.entity.Complaint;
import com.smartcomplaint.smartcomplaintportal.entity.ComplaintStatus;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceNotFoundException;
import com.smartcomplaint.smartcomplaintportal.mapper.ComplaintMapper;
import com.smartcomplaint.smartcomplaintportal.repository.ComplaintRepository;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.service.OfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.smartcomplaint.smartcomplaintportal.dto.OfficerDashboardDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficerServiceImpl implements OfficerService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    @Override
    public OfficerDashboardDto getDashboard() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User officer = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Officer not found"));

        List<Complaint> complaints = complaintRepository.findByAssignedOfficer(officer);

        long assigned = complaints.size();

        long pending = complaints.stream()
                .filter(c -> c.getStatus() == ComplaintStatus.PENDING)
                .count();

        long inProgress = complaints.stream()
                .filter(c -> c.getStatus() == ComplaintStatus.IN_PROGRESS)
                .count();

        long resolved = complaints.stream()
                .filter(c -> c.getStatus() == ComplaintStatus.RESOLVED)
                .count();

        return OfficerDashboardDto.builder()
                .assignedComplaints(assigned)
                .pendingComplaints(pending)
                .inProgressComplaints(inProgress)
                .resolvedComplaints(resolved)
                .build();
    }

    @Override
    public List<ComplaintResponseDto> getAssignedComplaints() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User officer = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Officer not found"));

        return complaintRepository.findByAssignedOfficer(officer)
                .stream()
                .map(ComplaintMapper::toResponseDto)
                .toList();
    }

    @Override
    public ComplaintResponseDto updateComplaintStatus(
            String complaintNumber,
            ComplaintStatusUpdateDto request) {

        Complaint complaint = complaintRepository
                .findByComplaintNumber(complaintNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found"));

        complaint.setStatus(request.getStatus());
        complaint.setOfficerRemarks(request.getRemarks());

        if (request.getStatus() == ComplaintStatus.RESOLVED) {
            complaint.setResolvedAt(LocalDateTime.now());
        }

        Complaint updatedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toResponseDto(updatedComplaint);
    }
}
