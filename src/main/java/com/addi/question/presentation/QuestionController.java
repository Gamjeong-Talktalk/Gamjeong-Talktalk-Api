package com.addi.question.presentation;

import com.addi.question.appication.QuestionService;
import com.addi.question.appication.dto.QuestionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping("/api/getQuestion")
	public ResponseEntity<QuestionResponse> getQuestionResponse() {
		QuestionResponse questionResponse = questionService.getQuestionService();
		System.out.println(questionResponse);

		return ResponseEntity.ok(questionResponse);

	}
}
