package com.addi.user.presentation;

import com.addi.user.application.ManagementService;
import com.addi.user.domain.Member;
import com.addi.user.dto.request.LoginToGuardianRequest;
import com.addi.user.dto.request.SignUpToGuardianRequest;
import com.addi.user.dto.request.SignUpToUserRequest;
import com.addi.user.dto.response.GetMembersResponse;
import com.addi.user.dto.response.LoginToGuardianResponse;
import com.addi.user.dto.response.LoginToUserResponse;
import com.addi.user.dto.response.SignUpToUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.addi.user.application.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class MemberAuthController {

	private final AuthService userAuthService;

	//유저 로그인
	@PostMapping("/api/login/user")
	public ResponseEntity<LoginToUserResponse> loginUser(@RequestHeader String registrationCode, @RequestBody String phoneNumber) {
		LoginToUserResponse response = userAuthService.loginToMember(registrationCode,phoneNumber);
		return ResponseEntity.ok(response);
	}

	//관리자 로그인
	@PostMapping("/api/login/guardian")
	public ResponseEntity<LoginToGuardianResponse> loginGuardian(LoginToGuardianRequest loginToGuardianRequest) {
		LoginToGuardianResponse response = userAuthService.loginToGuardian(loginToGuardianRequest);
		return ResponseEntity.ok(response);
	}

	// 유저 회원가입
	@PostMapping("/api/signup/user")
	public ResponseEntity<SignUpToUserResponse> signUpUser(@RequestHeader String registrationCode, SignUpToUserRequest signUpToUserRequest) {
		SignUpToUserResponse response = userAuthService.signUpToMember(registrationCode, signUpToUserRequest);

		return ResponseEntity.ok(response);
	}

	// 보호자 회원가입
	@PostMapping("/api/signup/guardian")
	public ResponseEntity<String> signUpGuardian(SignUpToGuardianRequest signUpToGuardianRequest) { // 보호자 회원가입
		String registrationCode = userAuthService.signUpToGuardian(signUpToGuardianRequest);

		return ResponseEntity.ok(registrationCode);
	}


}
