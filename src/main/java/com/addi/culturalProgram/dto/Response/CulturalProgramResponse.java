package com.addi.culturalProgram.dto.Response;


import com.addi.culturalProgram.domain.CulturalProgram;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CulturalProgramResponse {
    private Long programId;
    private String facilityName;
    private String address;
    private String phoneNumber;
    private String programName;
    private LocalDate startDate;
    private LocalDate endDate;

    public static CulturalProgramResponse toResponse (CulturalProgram culturalProgram){
        return CulturalProgramResponse.builder()
                .programId(culturalProgram.getId())
                .facilityName(culturalProgram.getFacilityName())
                .address(culturalProgram.getAddress())
                .phoneNumber(culturalProgram.getPhoneNumber())
                .programName(culturalProgram.getProgramName())
                .startDate(culturalProgram.getStartDate())
                .endDate(culturalProgram.getEndDate())
                .build();
    }
}
