package com.addi.conversation.domain;

import com.addi.global.auditing.BaseEntity;
import com.addi.user.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "CONVERSATION")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Conversation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="conversation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(nullable=false)
    private String question;

    @Column(nullable=false)
    private String response;

    @CreatedDate
    @Column(nullable=false)
    private LocalDate datetime;

    @Builder
    private Conversation(Member member, String question, String response) {
        this.member = member;
        this.question = question;
        this.response = response;
        this.datetime = LocalDate.now();
    }


}
