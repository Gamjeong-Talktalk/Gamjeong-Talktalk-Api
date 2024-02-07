package com.addi.user.application;

import com.addi.user.domain.Member;
import com.addi.user.dto.request.LoginToGuardianRequest;
import com.addi.user.dto.request.SignUpToGuardianRequest;
import com.addi.user.dto.request.SignUpToUserRequest;
import com.addi.user.dto.response.LoginToGuardianResponse;
import com.addi.user.dto.response.LoginToUserResponse;
import com.addi.user.dto.response.SignUpToUserResponse;
import com.addi.user.exception.GuardianError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addi.global.exception.BusinessException;
import com.addi.user.domain.Guardian;
import com.addi.user.exception.UserError;
import com.addi.user.infra.persistence.GuardianRepository;
import com.addi.user.infra.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

	private final MemberRepository memberRepository;
	private final GuardianRepository guardianRepository;

	//User 로그인
	public LoginToUserResponse loginToMember(String registrationCode, String phoneNumber){

		//부정확한 등록코드 일 시,
		Guardian guardian = guardianRepository.findByRegistrationCode(registrationCode)
				.orElseThrow(() -> BusinessException.of(UserError.INVITATION_CODE_NOT_FOUND));

		//해당 user가 존재하지 않을 시,
		Member member = memberRepository.findByGuardianAndPhoneNumber(guardian, phoneNumber)
				.orElseThrow(() -> BusinessException.of(UserError.NOT_SIGN_UP));

		return new LoginToUserResponse().toResponse(member);
	}

	//보호자 로그인
	public LoginToGuardianResponse loginToGuardian(LoginToGuardianRequest loginToGuardianRequest) {

		// 해당 Guardian이 존재하지 않을 시,
		Guardian guardian = guardianRepository.findByIDAndPassword(loginToGuardianRequest.getID(), loginToGuardianRequest.getPassword())
				.orElseThrow(() -> BusinessException.of(GuardianError.NOT_SIGN_UP));

		return new LoginToGuardianResponse().toResponse(guardian);
	}

	// User 회원가입
	public SignUpToUserResponse signUpToMember(String registrationCode, SignUpToUserRequest signUpToUserRequest) {

		//부정확한 등록코드 일 시,
		Guardian guardian = guardianRepository.findByRegistrationCode(registrationCode)
				.orElseThrow(() -> BusinessException.of(UserError.INVITATION_CODE_NOT_FOUND));

		Member member = signUpToUserRequest.toEntity(guardian);
		memberRepository.save(member);

		return new SignUpToUserResponse().toResponse(member);
	}

	//보호자 회원가입
	public String signUpToGuardian(SignUpToGuardianRequest signUpToGuardianRequest) {
		Optional<Guardian> optionalGuardian = guardianRepository.findByPhoneNumber(signUpToGuardianRequest.getPhoneNumber());

		if (optionalGuardian.isPresent()) {
			// 이미 존재하는 전화번호일 경우
			throw BusinessException.of(GuardianError.ALREADY_SIGN_UP);
		}

		Guardian guardian = signUpToGuardianRequest.toEntity();
		guardianRepository.save(guardian);

		return guardian.getRegistrationCode();
	}

}
