package com.addi.conversation.appication;

import com.addi.conversation.domain.Conversation;
import com.addi.conversation.infra.persistence.ConversationRepository;

import com.addi.global.exception.BusinessException;
import com.addi.member.domain.Member;
import com.addi.member.exception.MemberError;
import com.addi.member.infra.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConversationService {

	private final MemberRepository memberRepository;
	private final ConversationRepository conversationRepository;


	public List<Conversation> saveConversations(String identificationCode, List<String> responses){
		Optional<Member> member = memberRepository.findByIdentificationCode(identificationCode);

		//잘못된 식별코드
		if (!member.isPresent())
			throw BusinessException.of(MemberError.NOT_SIGN_UP);

		List<Conversation> Conversations = new ArrayList<>();

		// 대화 저장
		for (String response : responses) {
			Conversation conversation = Conversation.builder()
					.member(member.get())
					.response(response)
					.build();

			Conversations.add(conversationRepository.save(conversation)); // conversation 저장 및 List 추가
		}

		return Conversations;

	}
}