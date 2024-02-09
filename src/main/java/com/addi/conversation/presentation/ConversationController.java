package com.addi.conversation.presentation;

import java.util.List;

import com.addi.conversation.domain.Conversation;
import com.addi.emotion.application.EmotionService;
import com.addi.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
		@RequestParam("responses") List<String> responses
	) {
		//Conversation 생성
		List<Conversation> conversations = conversationService.saveConversations(identificationCode, responses);

		//Emotion 생성
		emotionService.emotionAnalyzeUsingHuggingFace(conversations);

		return ResponseEntity.ok().build();
	}
}
