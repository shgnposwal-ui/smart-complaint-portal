package com.smartcomplaint.smartcomplaintportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficerRemarksDto {

    @NotBlank(message = "Remarks cannot be empty")
    private String remarks;
}