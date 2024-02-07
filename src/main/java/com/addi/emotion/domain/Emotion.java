package com.addi.emotion.domain;


import com.addi.conversation.domain.Conversation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "EMOTION")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Emotion {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="emotion_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="conversation_id")
    private Conversation conversation;

    @Column(nullable=false, precision = 4, scale = 2)
    private BigDecimal anger;
    @Column(nullable=false, precision = 4, scale = 2)
    private BigDecimal sad;
    @Column(nullable=false, precision = 4, scale = 2)
    private BigDecimal anxious;
    @Column(nullable=false, precision = 4, scale = 2)
    private BigDecimal happy;
    @Column(nullable=false, precision = 4, scale = 2)
    private BigDecimal neutral;


    @Builder
    public Emotion(Conversation conversation, BigDecimal anger, BigDecimal sad, BigDecimal anxious, BigDecimal happy, BigDecimal neutral){
        this.conversation = conversation;
        this.anger = anger;
        this.sad = sad;
        this.anxious = anxious;
        this.happy = happy;
        this.neutral = neutral;
    }
}
