package com.smartcomplaint.smartcomplaintportal.dto;

import com.smartcomplaint.smartcomplaintportal.entity.ComplaintStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintStatusUpdateDto {

    @NotNull(message = "Status is required")
    private ComplaintStatus status;

    private String remarks;
}
