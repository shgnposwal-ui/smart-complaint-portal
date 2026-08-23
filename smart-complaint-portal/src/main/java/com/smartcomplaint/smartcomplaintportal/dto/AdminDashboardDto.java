package com.smartcomplaint.smartcomplaintportal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardDto {

    private long totalComplaints;
    private long pendingComplaints;
    private long inProgressComplaints;
    private long resolvedComplaints;

    private long highPriorityComplaints;

    private long totalCitizens;
    private long totalOfficers;
}
