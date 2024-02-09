package com.addi.emotion.presentation;

import com.addi.emotion.application.EmotionService;
import com.addi.conversation.infra.persistence.ConversationRepository;
import com.addi.emotion.dto.OneWeekEmotionResponse;
import com.addi.emotion.dto.TodayEmotionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class EmotionController {

    private final EmotionService emotionService;

    private final ConversationRepository conversationRepository;

    @GetMapping("/busan/sasang/today/{memberId}")
    public ResponseEntity<TodayEmotionResponse> getEmotionToday(@PathVariable Long memberId){
        TodayEmotionResponse todayEmotionResponse = emotionService.getEmotionToday(memberId);

        return ResponseEntity.ok(todayEmotionResponse);
    }

    @GetMapping("/busan/sasang/week/{memberId}")
    public ResponseEntity<OneWeekEmotionResponse> getEmotionOneWeek(@PathVariable Long memberId){
        OneWeekEmotionResponse oneWeekEmotionResponse = emotionService.getEmotionOneWeek(memberId);

        return ResponseEntity.ok(oneWeekEmotionResponse);
    }


}
