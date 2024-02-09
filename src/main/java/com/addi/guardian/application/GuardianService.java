package com.addi.guardian.application;

import com.addi.global.exception.BusinessException;
import com.addi.guardian.domain.Guardian;
import com.addi.guardian.dto.request.LoginToGuardianRequest;
import com.addi.guardian.dto.request.SignUpToGuardianRequest;
import com.addi.guardian.dto.response.LoginToGuardianResponse;
import com.addi.member.domain.Member;
import com.addi.guardian.dto.response.GetMembersResponse;
import com.addi.guardian.exception.GuardianError;
import com.addi.member.exception.MemberError;
import com.addi.guardian.infra.persistence.GuardianRepository;
import com.addi.member.infra.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GuardianService {

	private final MemberRepository memberRepository;
	private final GuardianRepository guardianRepository;

	//보호자 로그인
	public LoginToGuardianResponse loginToGuardian(LoginToGuardianRequest loginToGuardianRequest) {

		// 해당 Guardian이 존재하지 않을 시,
		Guardian guardian = guardianRepository.findByLoginIdAndPassword(loginToGuardianRequest.getLoginId(), loginToGuardianRequest.getPassword())
				.orElseThrow(() -> BusinessException.of(GuardianError.NOT_SIGN_UP));

		return new LoginToGuardianResponse().toResponse(guardian);
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



	//
	public List<GetMembersResponse> getMembmers(Long guardianId){
		Guardian guardian = guardianRepository.findById(guardianId)
				.orElseThrow(() -> BusinessException.of(GuardianError.NOT_EXIST_GUARDIAN));

		List<Member> memberList = guardian.getMember();

		return memberList.stream()
				.map(GetMembersResponse::toResponse)
				.collect(Collectors.toList());
	}

}
