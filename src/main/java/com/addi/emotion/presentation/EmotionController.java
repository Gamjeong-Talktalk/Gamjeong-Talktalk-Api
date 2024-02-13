package com.addi.emotion.presentation;

import com.addi.emotion.application.EmotionService;
import com.addi.conversation.infra.persistence.ConversationRepository;
import com.addi.emotion.dto.OneWeekEmotionResponse;
import com.addi.emotion.dto.TodayEmotionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class EmotionController {

    private final EmotionService emotionService;

    private final ConversationRepository conversationRepository;

    /*@GetMapping("/busan/sasang/today/{memberId}")
    public ResponseEntity<TodayEmotionResponse> getEmotionToday(@PathVariable Long memberId){
        TodayEmotionResponse todayEmotionResponse = emotionService.getEmotionToday(memberId);

        return ResponseEntity.ok(todayEmotionResponse);
    }*/

    @GetMapping("/busan/sasang/week/{memberId}")
    public String getEmotionOneWeek(Model model, @PathVariable Long memberId){
        OneWeekEmotionResponse oneWeekEmotionResponse = emotionService.getEmotionOneWeek(memberId);
        model.addAttribute("emotionResponse", oneWeekEmotionResponse);
        model.addAttribute("emotionDateNow",LocalDate.now().minusDays(1).toString());
        model.addAttribute("emotionDatePast",LocalDate.now().minusDays(8).toString());


        return "emotion/weekEmotion";

    }

    @GetMapping("/busan/sasang/today/{memberId}")
    public String showEmotion(Model model, @PathVariable Long memberId) {
        TodayEmotionResponse todayEmotionResponse = emotionService.getEmotionToday(memberId);
        model.addAttribute("emotionResponse", todayEmotionResponse);
        model.addAttribute("emotionDate", LocalDate.now().minusDays(1).toString());

        return "emotion/todayEmotion"; // 이 부분에서는 위에서 작성한 Thymeleaf HTML 파일의 이름을 반환합니다.
    }


}
