package com.addi.culturalProgram.dto.Response;

import com.addi.culturalProgram.domain.CulturalProgram;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static CulturalProgramResponse toResponse (CulturalProgram culturalProgram){
        return CulturalProgramResponse.builder()
                .programId(culturalProgram.getId())
                .facilityName(culturalProgram.getFacilityName())
                .address(culturalProgram.getAddress())
                .phoneNumber(culturalProgram.getPhoneNumber())
                .programName(culturalProgram.getProgramName())
                .build();
    }
}
