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
	@PostMapping("/busan/sasang/login/member")
	public ResponseEntity<LoginToUserResponse> loginUser(@RequestHeader String identificationCode) {
		LoginToUserResponse response = memberService.loginToMember(identificationCode);

		return ResponseEntity.ok(response);
	}

	// 유저 회원가입
	@PostMapping("/busan/sasang/signup/member")
	public ResponseEntity<SignUpToUserResponse> signUpUser(@RequestHeader String registrationCode, @RequestBody SignUpToUserRequest signUpToUserRequest) {
		SignUpToUserResponse response = memberService.signUpToMember(registrationCode, signUpToUserRequest);

		return ResponseEntity.ok(response);
	}

	// SOS 요청 송신
	@GetMapping("/busan/sasang/sos/{memberId}")
	public ResponseEntity<LoginToUserResponse> sendSOSRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);

		return ResponseEntity.ok().build();
	}

	//가사지원
	@GetMapping("/busan/sasang/chores/{memberId}")
	public ResponseEntity<LoginToUserResponse> sendChoreRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);

		return ResponseEntity.ok().build();
	}

	//외출동행
	@GetMapping("/busan/sasang/outside/{memberId}")
	public ResponseEntity<LoginToUserResponse> sendOutsideRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);

		return ResponseEntity.ok().build();
	}

	//말벗
	@GetMapping("/busan/sasang/friend/{memberId}")
	public ResponseEntity<LoginToUserResponse> sendFriendRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);

		return ResponseEntity.ok().build();
	}

	//문화 활동 신청
	@GetMapping("/busan/sasang/culture/{memberId}")
	public ResponseEntity<LoginToUserResponse> sendCultureRequest(@PathVariable Long memberId){
		memberService.sendRequest(memberId);

		return ResponseEntity.ok().build();
	}


}
