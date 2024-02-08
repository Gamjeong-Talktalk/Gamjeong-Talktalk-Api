package com.addi.member.presentation;

import com.addi.guardian.dto.request.LoginToGuardianRequest;
import com.addi.guardian.dto.request.SignUpToGuardianRequest;
import com.addi.member.dto.request.SignUpToUserRequest;
import com.addi.guardian.dto.response.LoginToGuardianResponse;
import com.addi.member.dto.response.LoginToUserResponse;
import com.addi.member.dto.response.SignUpToUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.addi.member.application.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	//유저 로그인
	@PostMapping("/api/login/user")
	public ResponseEntity<LoginToUserResponse> loginUser(@RequestHeader String registrationCode, @RequestBody String phoneNumber) {
		LoginToUserResponse response = memberService.loginToMember(registrationCode,phoneNumber);
		return ResponseEntity.ok(response);
	}

	// 유저 회원가입
	@PostMapping("/api/signup/user")
	public ResponseEntity<SignUpToUserResponse> signUpUser(@RequestHeader String registrationCode, SignUpToUserRequest signUpToUserRequest) {
		SignUpToUserResponse response = memberService.signUpToMember(registrationCode, signUpToUserRequest);

		return ResponseEntity.ok(response);
	}

	// SOS 요청 송신
	@GetMapping("/api/request/sos/{memberId}")
	public void sendSOSRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);
	}

	//가사지원
	@GetMapping("/api/request/chores/{memberId}")
	public void sendChoreRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);
	}

	//외출동행
	@GetMapping("/api/request/outside/{memberId}")
	public void sendOutsideRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);
	}

	//말벗
	@GetMapping("/api/request/friend/{memberId}")
	public void sendFriendRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);
	}

	//문화 활동 신청
	@GetMapping("/api/request/culture/{memberId}")
	public void sendCultureRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);
	}


}
