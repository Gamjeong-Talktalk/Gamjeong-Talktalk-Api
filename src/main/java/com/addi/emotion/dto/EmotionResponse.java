package com.addi.emotion.dto;

import com.addi.emotion.domain.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EmotionResponse {
    BigDecimal anger;
    BigDecimal sad;
    BigDecimal anxious;
    BigDecimal happy;
    BigDecimal neutral;


    public static EmotionResponse toResponse(Emotion emotion){
        return EmotionResponse.builder()
                .anger(emotion.getAnger())
                .sad(emotion.getSad())
                .anxious(emotion.getAnxious())
                .happy(emotion.getHappy())
                .neutral(emotion.getNeutral())
                .build();
    }
}
