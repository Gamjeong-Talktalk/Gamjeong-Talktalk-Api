package com.addi.culturalProgram.application;

import com.addi.culturalProgram.domain.CulturalProgram;
import com.addi.culturalProgram.dto.Request.CulturalProgramRequest;
import com.addi.culturalProgram.dto.Response.CulturalProgramResponse;
import com.addi.culturalProgram.exception.CulturalProgramError;
import com.addi.culturalProgram.infra.persistence.CulturalProgramRepository;
import com.addi.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CulturalProgramService {

    private final CulturalProgramRepository culturalProgramRepository;

    public void createCulturalProgram(CulturalProgramRequest programDTO) {
        CulturalProgram program = CulturalProgram.builder()
                .facilityName(programDTO.getFacilityName())
                .address(programDTO.getAddress())
                .phoneNumber(programDTO.getPhoneNumber())
                .programName(programDTO.getProgramName())
                .startDate(programDTO.getStartDate())
                .endDate(programDTO.getEndDate())
                .build();

        CulturalProgram savedProgram = culturalProgramRepository.save(program);
    }

    public List<CulturalProgramResponse> getAllCulturalPrograms() {
        LocalDate today = LocalDate.now();
        List<CulturalProgram> programs = culturalProgramRepository.findByEndDateAfter(today);

        if(programs.isEmpty())
            throw BusinessException.of(CulturalProgramError.NOT_EXIST_PROGRAM); // 등록된 프로그램이 없을 경우.

        return programs.stream()
                .map(CulturalProgramResponse::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteCulturalProgram(Long programId) {
        culturalProgramRepository.deleteById(programId);
    }

}
