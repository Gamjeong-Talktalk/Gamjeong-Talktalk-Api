package com.addi.culturalProgram.presentation;

import com.addi.culturalProgram.application.CulturalProgramService;
import com.addi.culturalProgram.dto.Request.CulturalProgramRequest;
import com.addi.culturalProgram.dto.Response.CulturalProgramResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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



    @GetMapping("/cultures/new")
    public String createForm(Model model) {
        model.addAttribute("culturalForm", new CulturalProgramRequest());
        return "cultures/createCultureForm";
    }

    @PostMapping("/cultures/new")
    public String create(@Valid CulturalProgramRequest culturalForm) {
        culturalProgramService.createCulturalProgram(culturalForm);
        return "redirect:/";
    }

    @GetMapping("/cultures")
    public String list(Model model) {
        List<CulturalProgramResponse> cultures = culturalProgramService.getAllCulturalPrograms();
        model.addAttribute("cultures", cultures);
        return "cultures/culturesList";
    }
}
