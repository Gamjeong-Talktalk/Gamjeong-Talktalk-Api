package com.addi.culturalProgram.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CulturalProgramRequest {

    private String facilityName;
    private String address;
    private String phoneNumber;
    private String programName;
    private LocalDate startDate;
    private LocalDate endDate;

}
