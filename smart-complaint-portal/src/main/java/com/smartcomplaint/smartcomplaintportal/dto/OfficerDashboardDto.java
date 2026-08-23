package com.smartcomplaint.smartcomplaintportal.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficerDashboardDto {

    private Long assignedComplaints;

    private Long pendingComplaints;

    private Long inProgressComplaints;

    private Long resolvedComplaints;

}