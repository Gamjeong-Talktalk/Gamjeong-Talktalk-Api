package com.addi.emotion.presentation;

import com.addi.emotion.application.EmotionService;
import com.addi.conversation.domain.Conversation;
import com.addi.conversation.infra.persistence.ConversationRepository;
import com.addi.emotion.dto.EmotionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EmotionController {

    private final EmotionService emotionService;

    private final ConversationRepository conversationRepository;

    @PostMapping("/test")
    public ResponseEntity<String> signUpUser(@RequestBody String name) {

        Optional<Conversation> conversation = conversationRepository.findById(Long.valueOf(1));
        emotionService.EmotionAnalyzeUsingHuggingFace(conversation.get()); //테스트용입니다.


        return ResponseEntity.ok("Success");
    }


    @GetMapping("/api/emotion/today/{memberId}")
    public ResponseEntity<EmotionResponse> getEmotionToday(@PathVariable Long memberId){
        EmotionResponse emotionResponse = emotionService.getEmotionToday(memberId);

        return ResponseEntity.ok(emotionResponse);
    }

    @GetMapping("/api/emotion/week/{memberId}")
    public ResponseEntity<EmotionResponse> getEmotionOneWeek(@PathVariable Long memberId){
        EmotionResponse emotionResponse = emotionService.getEmotionOneWeek(memberId);

        return ResponseEntity.ok(emotionResponse);
    }


}
