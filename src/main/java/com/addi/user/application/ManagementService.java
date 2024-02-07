package com.addi.user.application;

import com.addi.global.exception.BusinessException;
import com.addi.user.domain.Guardian;
import com.addi.user.domain.Member;
import com.addi.user.dto.response.GetMembersResponse;
import com.addi.user.exception.GuardianError;
import com.addi.user.exception.UserError;
import com.addi.user.infra.persistence.GuardianRepository;
import com.addi.user.infra.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagementService {

	private final MemberRepository memberRepository;
	private final GuardianRepository guardianRepository;


	//
	public List<GetMembersResponse> getMembmers(Long guardianId){
		Guardian guardian = guardianRepository.findById(guardianId)
				.orElseThrow(() -> BusinessException.of(GuardianError.NOT_EXIST_GUARDIAN));

		List<Member> memberList = guardian.getMember();

		return memberList.stream()
				.map(GetMembersResponse::toResponse)
				.collect(Collectors.toList());
	}

	// 요청 보내기 (SOS, 가사지원, 외출동행, 말벗, 문화활동신청)
	public void sendRequest(Long memberId){
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> BusinessException.of(UserError.NOT_EXIST_MEMBER));

		return;
	}
}
