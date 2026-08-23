package com.smartcomplaint.smartcomplaintportal.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardResponseDto {

    private AdminDashboardDto statistics;

    private List<ComplaintResponseDto> recentComplaints;

    private List<OfficerSummaryDto> officers;
}
