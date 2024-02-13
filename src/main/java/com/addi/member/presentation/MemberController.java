package com.addi.member.presentation;

import com.addi.guardian.dto.request.LoginToGuardianRequest;
import com.addi.guardian.dto.request.SignUpToGuardianRequest;
import com.addi.member.dto.request.SignUpToUserRequest;
import com.addi.guardian.dto.response.LoginToGuardianResponse;
import com.addi.member.dto.response.LoginToUserResponse;
import com.addi.member.dto.response.SignUpToUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.addi.member.application.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	//유저 로그인
	@GetMapping("/busan/sasang/login/member")
	public ResponseEntity<LoginToUserResponse> loginUser(@RequestHeader String identificationCode) {
		LoginToUserResponse response = memberService.loginToMember(identificationCode);

		return ResponseEntity.ok(response);
	}

	// 유저 회원가입
	@PostMapping("/busan/sasang/signup/member")
	public ResponseEntity<SignUpToUserResponse> signUpUser(@RequestBody SignUpToUserRequest signUpToUserRequest) {
		SignUpToUserResponse response = memberService.signUpToMember(signUpToUserRequest);

		return ResponseEntity.ok(response);
	}//

	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new SignUpToUserRequest());
		return "members/createMemberForm";
	}

	@PostMapping("/members/new")
	public String create(
			@Valid SignUpToUserRequest memberForm,
			BindingResult result
	) {

		if (result.hasErrors()) {
			return "members/createMemberForm";
		}
		memberService.signUpToMember(memberForm);
		return "redirect:/";
	}


}
