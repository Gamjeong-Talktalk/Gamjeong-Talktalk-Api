package com.addi.emotion.infra.persistence;

import com.addi.emotion.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionRepository extends JpaRepository<Emotion,Long> {

    List<Emotion> findByConversationIdIn(List<Long> conversationIds);
}
