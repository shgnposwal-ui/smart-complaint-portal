package com.smartcomplaint.smartcomplaintportal.service.impl;

import com.smartcomplaint.smartcomplaintportal.dto.ComplaintRequestDto;
import com.smartcomplaint.smartcomplaintportal.dto.ComplaintResponseDto;
import com.smartcomplaint.smartcomplaintportal.entity.ComplaintStatus;
import com.smartcomplaint.smartcomplaintportal.exception.ResourceNotFoundException;
import com.smartcomplaint.smartcomplaintportal.repository.ComplaintRepository;
import com.smartcomplaint.smartcomplaintportal.repository.UserRepository;
import com.smartcomplaint.smartcomplaintportal.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.smartcomplaint.smartcomplaintportal.entity.Complaint;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import com.smartcomplaint.smartcomplaintportal.mapper.ComplaintMapper;
import com.smartcomplaint.smartcomplaintportal.util.ComplaintNumberGenerator;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;



@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    @Override
    public ComplaintResponseDto createComplaint(ComplaintRequestDto request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
         new ResourceNotFoundException("User not found"));

        Complaint complaint = ComplaintMapper.toEntity(request);

        complaint.setCitizen(citizen);

        complaint.setComplaintNumber(
                ComplaintNumberGenerator.generate()
        );

        Complaint savedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toResponseDto(savedComplaint);
    }

    @Override
    public List<ComplaintResponseDto> getMyComplaints() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
         new ResourceNotFoundException("User not found"));

        return complaintRepository.findByCitizen(citizen)
                .stream()
                .map(ComplaintMapper::toResponseDto)
                .toList();
    }

    @Override
    public ComplaintResponseDto getComplaintByNumber(String complaintNumber) {

        Complaint complaint = complaintRepository.findByComplaintNumber(complaintNumber)
                .orElseThrow(() ->
         new ResourceNotFoundException("Complaint not found"));

        return ComplaintMapper.toResponseDto(complaint);
    }
    @Override
    public List<ComplaintResponseDto> getAllComplaints() {

        return complaintRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(ComplaintMapper::toResponseDto)
                .toList();
    }
    @Override
    public ComplaintResponseDto assignOfficer(
            String complaintNumber,
            Long officerId) {

        Complaint complaint = complaintRepository
                .findByComplaintNumber(complaintNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found"));

        User officer = userRepository
                .findById(officerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Officer not found"));

        complaint.setAssignedOfficer(officer);
        complaint.setStatus(ComplaintStatus.ASSIGNED);

        Complaint savedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toResponseDto(savedComplaint);
    }
}
