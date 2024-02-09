package com.addi.conversation.presentation;

import java.util.List;

import com.addi.conversation.domain.Conversation;
import com.addi.conversation.dto.ConversationRequest;
import com.addi.emotion.application.EmotionService;
import com.addi.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.addi.conversation.appication.ConversationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConversationController {

	private final ConversationService conversationService;

	private final EmotionService emotionService;

	@PostMapping("/busan/sasang/conversation")
	public ResponseEntity<Void> toEmotionalAnalysisResponse(
		@RequestHeader String identificationCode,
		@RequestBody ConversationRequest conversationRequest
	) {
		//Conversation 생성
		List<Conversation> conversations = conversationService.saveConversations(identificationCode, conversationRequest);

		//Emotion 생성
		emotionService.emotionAnalyzeUsingHuggingFace(conversations);

		return ResponseEntity.ok().build();
	}
}
