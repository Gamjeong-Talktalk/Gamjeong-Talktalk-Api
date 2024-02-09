package com.addi.culturalProgram.presentation;

import com.addi.culturalProgram.application.CulturalProgramService;
import com.addi.culturalProgram.dto.Request.CulturalProgramRequest;
import com.addi.culturalProgram.dto.Response.CulturalProgramResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CulturalProgramController {

    private final CulturalProgramService culturalProgramService;

    @PostMapping("/busan/sasang/program")
    public ResponseEntity<Void> createCulturalProgram(@RequestBody CulturalProgramRequest programDTO) {
        culturalProgramService.createCulturalProgram(programDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/busan/sasang/programs")
    public ResponseEntity<List<CulturalProgramResponse>> getAllCulturalPrograms() {
        List<CulturalProgramResponse> programs = culturalProgramService.getAllCulturalPrograms();
        return ResponseEntity.ok(programs);
    }

    @DeleteMapping("/busan/sasang/program/{programId}")
    public ResponseEntity<Void> deleteCulturalProgram(@PathVariable Long programId) {
        culturalProgramService.deleteCulturalProgram(programId);
        return ResponseEntity.noContent().build();
    }
}
