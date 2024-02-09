package com.addi.emotion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OneWeekEmotionResponse {
    private Map<String, BigDecimal> max;
    private Map<String, BigDecimal> min;
    private Map<String, BigDecimal> avg;

    // 생성자, 게터, 세터 생략

    public static OneWeekEmotionResponse toResponse(Map<String, BigDecimal> max, Map<String, BigDecimal> min, Map<String, BigDecimal> avg){
        return new OneWeekEmotionResponse().builder()
                .max(max) // 최댓값
                .min(min) // 최솟값
                .avg(avg) // 평균값
                .build();
    }
}
