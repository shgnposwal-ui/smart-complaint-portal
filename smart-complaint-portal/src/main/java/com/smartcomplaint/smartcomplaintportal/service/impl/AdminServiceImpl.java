package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.*;
import com.smartcomplaint.smartcomplaintportal.entity.*;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceNotFoundException;
import com.smartcomplaint.smartcomplaintportal.mapper.ComplaintMapper;
import com.smartcomplaint.smartcomplaintportal.repository.ComplaintRepository;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.smartcomplaint.smartcomplaintportal.dto.CitizenSummaryDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    @Override
    public List<ComplaintResponseDto> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(ComplaintMapper::toResponseDto)
                .toList();
    }
    @Override
    public List<OfficerSummaryDto> getAllOfficers() {

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.OFFICER)
                .map(user -> OfficerSummaryDto.builder()
                        .id(user.getId())
                        .fullName(
                                user.getFirstName()
                                        + " "
                                        + user.getLastName()
                        )
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .city(user.getCity())
                        .state(user.getState())
                        .isActive(user.getIsActive())
                        .build())
                .toList();
    }
    @Override
    public OfficerSummaryDto updateOfficerStatus(Long id, boolean active) {

        User officer = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Officer not found"));

        if (officer.getRole() != Role.OFFICER) {
            throw new IllegalArgumentException(
                    "Selected user is not an officer"
            );
        }

        officer.setIsActive(active);

        User savedOfficer = userRepository.save(officer);

        return OfficerSummaryDto.builder()
                .id(savedOfficer.getId())
                .fullName(
                        savedOfficer.getFirstName()
                                + " "
                                + savedOfficer.getLastName()
                )
                .email(savedOfficer.getEmail())
                .phoneNumber(savedOfficer.getPhoneNumber())
                .city(savedOfficer.getCity())
                .state(savedOfficer.getState())
                .isActive(savedOfficer.getIsActive())
                .build();
    }
    @Override
    public List<CitizenSummaryDto> getAllCitizens() {

        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.CITIZEN)
                .map(user -> CitizenSummaryDto.builder()
                        .id(user.getId())
                        .fullName(user.getFirstName() + " " + user.getLastName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .city(user.getCity())
                        .state(user.getState())
                        .isActive(user.getIsActive())
                        .build())
                .toList();

    }




    @Override
    public AdminDashboardDto getDashboardStatistics() {

        return AdminDashboardDto.builder()
                .totalComplaints(complaintRepository.count())
                .pendingComplaints(
                        complaintRepository.countByStatus(ComplaintStatus.PENDING)
                )
                .inProgressComplaints(
                        complaintRepository.countByStatus(ComplaintStatus.IN_PROGRESS)
                )
                .resolvedComplaints(
                        complaintRepository.countByStatus(ComplaintStatus.RESOLVED)
                )
                .highPriorityComplaints(
                        complaintRepository.countByPriority(Priority.HIGH)
                )
                .totalCitizens(
                        userRepository.countByRole(Role.CITIZEN)
                )
                .totalOfficers(
                        userRepository.countByRole(Role.OFFICER)
                )
                .build();
    }
    @Override
    public ComplaintResponseDto assignOfficer(
            String complaintNumber,
            Long officerId) {

        Complaint complaint = complaintRepository
                .findByComplaintNumber(complaintNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found"));

        User officer = userRepository.findById(officerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Officer not found"));

        if (officer.getRole() != Role.OFFICER) {
            throw new IllegalArgumentException("Selected user is not an officer");
        }

        complaint.setAssignedOfficer(officer);

        if (complaint.getStatus() == ComplaintStatus.PENDING) {
            complaint.setStatus(ComplaintStatus.IN_PROGRESS);
        }

        Complaint savedComplaint = complaintRepository.save(complaint);

        ComplaintResponseDto dto = ComplaintMapper.toResponseDto(savedComplaint);

        System.out.println(dto);

        return dto;
    }
    @Override
    public AdminDashboardResponseDto getDashboard() {

        List<ComplaintResponseDto> recentComplaints =
                complaintRepository.findAll()
                        .stream()
                        .sorted((a, b) ->
                                b.getCreatedAt().compareTo(a.getCreatedAt()))
                        .limit(5)
                        .map(ComplaintMapper::toResponseDto)
                        .toList();

        return AdminDashboardResponseDto.builder()
                .statistics(getDashboardStatistics())
                .recentComplaints(recentComplaints)
                .officers(getAllOfficers())
                .build();
    }
}