package com.addi.member.application;

import com.addi.member.domain.Member;
import com.addi.guardian.dto.request.LoginToGuardianRequest;
import com.addi.guardian.dto.request.SignUpToGuardianRequest;
import com.addi.member.dto.request.SignUpToUserRequest;
import com.addi.guardian.dto.response.LoginToGuardianResponse;
import com.addi.member.dto.response.LoginToUserResponse;
import com.addi.member.dto.response.SignUpToUserResponse;
import com.addi.guardian.exception.GuardianError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addi.global.exception.BusinessException;
import com.addi.guardian.domain.Guardian;
import com.addi.member.exception.MemberError;
import com.addi.guardian.infra.persistence.GuardianRepository;
import com.addi.member.infra.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	private final GuardianRepository guardianRepository;

	//User 로그인
	public LoginToUserResponse loginToMember(String identificationCode){

		//해당 user가 존재하지 않을 시,
		Member member = memberRepository.findByIdentificationCode(identificationCode)
				.orElseThrow(() -> BusinessException.of(MemberError.NOT_SIGN_UP));

		return new LoginToUserResponse().toResponse(member);
	}

	// User 회원가입
	public SignUpToUserResponse signUpToMember(String registrationCode, SignUpToUserRequest signUpToUserRequest) {

		//부정확한 등록코드 일 시,
		Guardian guardian = guardianRepository.findByRegistrationCode(registrationCode)
				.orElseThrow(() -> BusinessException.of(MemberError.INVITATION_CODE_NOT_FOUND));

		Member member = signUpToUserRequest.toEntity(guardian);
		memberRepository.save(member);

		return new SignUpToUserResponse().toResponse(member);
	}

	// 요청 보내기 (SOS, 가사지원, 외출동행, 말벗, 문화활동신청)
	public void sendRequest(Long memberId){
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> BusinessException.of(MemberError.NOT_EXIST_MEMBER));

		return;
	}

}
