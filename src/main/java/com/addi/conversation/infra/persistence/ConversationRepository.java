package com.addi.conversation.infra.persistence;

import com.addi.conversation.domain.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {


    List<Conversation> findByMemberIdAndDatetime(Long memberId, LocalDate yesterday);
    List<Conversation> findByMemberIdAndDatetimeBetween(Long memberId, LocalDate startDate, LocalDate endDate);


}
