package com.addi.global.scheduler;

import com.addi.conversation.domain.Conversation;
import com.addi.conversation.infra.persistence.ConversationRepository;
import com.addi.emotion.domain.Emotion;
import com.addi.emotion.infra.persistence.EmotionRepository;
import com.addi.member.domain.Member;
import com.addi.member.infra.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class DailyDepressionindexUpdater {
    private final MemberRepository memberRepository;
    private final ConversationRepository conversationRepository;
    private final EmotionRepository emotionRepository;


    @Scheduled(cron = "0 0 0 * * *") // 매일 0시 0분 0초에 실행.
    public void updateDepressionIndex(){ // 우울지수 업데이트 메서드
        List<Member> members = memberRepository.findAll();

        for (Member member : members) { //전체 멤버에 대해서 수행
            BigDecimal updatedDepressionIndex = calculateNewDepressionIndex(member);
            member.setDepressionIndex(updatedDepressionIndex);
            memberRepository.save(member);
        }
    }

    private BigDecimal calculateNewDepressionIndex(Member member) {
        LocalDate endDate = LocalDate.now(); // 현재 날짜
        LocalDate startDate = endDate.minusWeeks(1); // 일주일 전 날짜
        Double depressionIndex = Double.valueOf(0);

        List<Conversation> conversations = conversationRepository.findByMemberIdAndDatetimeBetween(member.getId(), startDate, endDate); // 일주일간의 대화 데이터 검색

        for(Conversation conversation : conversations){ // 대화 데이터들의 감정 추출
            Optional<Emotion> emotion = emotionRepository.findById(conversation.getId());
            depressionIndex += (emotion.get().getAnger().doubleValue() + emotion.get().getSad().doubleValue() + emotion.get().getAnxious().doubleValue()); // 우울 지수 = 분노(Anger) + 슬픔(Sad) + 불안(Anxious)
        }

        depressionIndex /= conversations.size(); // 우울지수 평균계산

        return BigDecimal.valueOf(depressionIndex);
    }
}
