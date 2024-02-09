package com.addi.guardian.presentation;

import com.addi.guardian.application.GuardianService;
import com.addi.guardian.dto.request.LoginToGuardianRequest;
import com.addi.guardian.dto.request.SignUpToGuardianRequest;
import com.addi.guardian.dto.response.GetMembersResponse;
import com.addi.guardian.dto.response.LoginToGuardianResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class GuardianController {

    private final GuardianService guardianService;

    //관리자 로그인
    @PostMapping("/busan/sasang/login/guardian")
    public ResponseEntity<LoginToGuardianResponse> loginGuardian(@RequestBody LoginToGuardianRequest loginToGuardianRequest) {
        LoginToGuardianResponse response = guardianService.loginToGuardian(loginToGuardianRequest);
        return ResponseEntity.ok(response);
    }

    // 보호자 회원가입
    @PostMapping("/busan/sasang/signup/guardian")
    public ResponseEntity<String> signUpGuardian(@RequestBody SignUpToGuardianRequest signUpToGuardianRequest) { // 보호자 회원가입
        String registrationCode = guardianService.signUpToGuardian(signUpToGuardianRequest);

        return ResponseEntity.ok(registrationCode);
    }

    //보호자관리 대상 유저 정보
    @GetMapping("/busan/sasang/members/{guardianId}")
    public ResponseEntity<List<GetMembersResponse>> getMembers(@PathVariable Long guardianId){
        List<GetMembersResponse> membersResponses = guardianService.getMembmers(guardianId);

        return ResponseEntity.ok(membersResponses);
    }

}
