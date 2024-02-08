package com.addi.guardian.presentation;

import com.addi.guardian.application.GuardianService;
import com.addi.guardian.dto.request.LoginToGuardianRequest;
import com.addi.guardian.dto.request.SignUpToGuardianRequest;
import com.addi.guardian.dto.response.GetMembersResponse;
import com.addi.guardian.dto.response.LoginToGuardianResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class GuardianController {

    private final GuardianService guardianService;

    //관리자 로그인
    @PostMapping("/api/login/guardian")
    public ResponseEntity<LoginToGuardianResponse> loginGuardian(LoginToGuardianRequest loginToGuardianRequest) {
        LoginToGuardianResponse response = guardianService.loginToGuardian(loginToGuardianRequest);
        return ResponseEntity.ok(response);
    }

    // 보호자 회원가입
    @PostMapping("/api/signup/guardian")
    public ResponseEntity<String> signUpGuardian(SignUpToGuardianRequest signUpToGuardianRequest) { // 보호자 회원가입
        String registrationCode = guardianService.signUpToGuardian(signUpToGuardianRequest);

        return ResponseEntity.ok(registrationCode);
    }

    //보호자관리 대상 유저 정보
    @GetMapping("/api/members/{guardianId}")
    public ResponseEntity<List<GetMembersResponse>> getMembers(@PathVariable Long guardianId){
        List<GetMembersResponse> membersResponses = guardianService.getMembmers(guardianId);

        return ResponseEntity.ok(membersResponses);
    }

}
