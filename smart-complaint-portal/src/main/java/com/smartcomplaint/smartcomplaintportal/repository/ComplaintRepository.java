package com.smartcomplaint.smartcomplaintportal.repository;

import com.smartcomplaint.smartcomplaintportal.entity.Complaint;
import com.smartcomplaint.smartcomplaintportal.entity.Priority;
import com.smartcomplaint.smartcomplaintportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcomplaint.smartcomplaintportal.entity.ComplaintStatus;
import java.util.List;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Optional<Complaint> findByComplaintNumber(String complaintNumber);

    List<Complaint> findByCitizen(User citizen);
    List<Complaint> findByAssignedOfficer(User assignedOfficer);
    List<Complaint> findAllByOrderByCreatedAtDesc();

    long countByAssignedOfficer(User assignedOfficer);
    long countByStatus(ComplaintStatus status);

    long countByPriority(Priority priority);

    long countByAssignedOfficerAndStatus(
            User assignedOfficer,
            ComplaintStatus status
    );
}
