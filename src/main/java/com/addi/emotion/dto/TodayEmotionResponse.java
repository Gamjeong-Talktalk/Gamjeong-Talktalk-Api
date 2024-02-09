package com.addi.emotion.dto;

import com.addi.emotion.domain.Emotion;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TodayEmotionResponse {
    BigDecimal anger;
    BigDecimal sad;
    BigDecimal anxious;
    BigDecimal happy;
    BigDecimal neutral;


    public static TodayEmotionResponse toResponse(Emotion emotion){
        return TodayEmotionResponse.builder()
                .anger(emotion.getAnger())
                .sad(emotion.getSad())
                .anxious(emotion.getAnxious())
                .happy(emotion.getHappy())
                .neutral(emotion.getNeutral())
                .build();
    }
}