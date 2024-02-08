package com.addi.emotion.application;

import com.addi.conversation.exception.ConversationError;
import com.addi.conversation.infra.persistence.ConversationRepository;
import com.addi.emotion.domain.Emotion;
import com.addi.emotion.dto.EmotionResponse;
import com.addi.emotion.infra.persistence.EmotionRepository;
import com.addi.conversation.domain.Conversation;
import com.addi.global.exception.BusinessException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmotionService {

    @Value("${huggingface.token}")
    private String token;

    private final ConversationRepository conversationRepository;
    private final EmotionRepository emotionRepository;
    private final RestTemplate restTemplate;


    public EmotionResponse getEmotionToday(Long memberId){
        LocalDate currentDate = LocalDate.now().minusDays(1); // 현재날짜 기준 어제

        BigDecimal anger= BigDecimal.valueOf(0);
        BigDecimal sad= BigDecimal.valueOf(0);
        BigDecimal anxious= BigDecimal.valueOf(0);
        BigDecimal happy= BigDecimal.valueOf(0);
        BigDecimal neutral= BigDecimal.valueOf(0);

        // 일주일간 대화 데이터 추출
        List<Conversation> conversationList = conversationRepository.findByMemberIdAndDatetime(memberId, currentDate);
        if (conversationList.isEmpty()) {
            throw BusinessException.of(ConversationError.NOT_EXIST_CONVERSATION); // 저장된 대화가 없을 경우
        }

        // 대화 Entity의 Id 추출
        List<Long> conversationIds = new ArrayList<>(); // 대화 id 추출 리스트
        for(Conversation conversation : conversationList) {
            conversationIds.add(conversation.getId());
        }

        // 대화들의 감정 추출
        List<Emotion> emotionList = emotionRepository.findByConversationIdIn(conversationIds);
        for(Emotion emotion : emotionList){
            anger = anger.add(emotion.getAnger());
            sad = sad.add(emotion.getSad());
            anxious = anxious.add(emotion.getAnxious());
            happy = happy.add(emotion.getHappy());
            neutral = neutral.add(emotion.getNeutral());
        }


        return EmotionResponse.builder()
                .anger(anger.divide(BigDecimal.valueOf(emotionList.size())))
                .sad(sad.divide(BigDecimal.valueOf(emotionList.size())))
                .anxious(anxious.divide(BigDecimal.valueOf(emotionList.size())))
                .happy(happy.divide(BigDecimal.valueOf(emotionList.size())))
                .neutral(neutral.divide(BigDecimal.valueOf(emotionList.size())))
                .build();
    }

    public EmotionResponse getEmotionOneWeek(Long memberId){
        LocalDate currentDate = LocalDate.now().minusDays(1); // 현재 날짜
        LocalDate oneWeekAgoDate = currentDate.minusWeeks(1); // 일주일 전의 날짜

        BigDecimal anger= BigDecimal.valueOf(0);
        BigDecimal sad= BigDecimal.valueOf(0);
        BigDecimal anxious= BigDecimal.valueOf(0);
        BigDecimal happy= BigDecimal.valueOf(0);
        BigDecimal neutral= BigDecimal.valueOf(0);

        // 일주일간 대화 데이터 추출
        List<Conversation> conversationList = conversationRepository.findByMemberIdAndDatetimeBetween(memberId, currentDate, oneWeekAgoDate);
        if (conversationList.isEmpty()) {
            throw BusinessException.of(ConversationError.NOT_EXIST_CONVERSATION); // 저장된 대화가 없을 경우
        }

        // 대화 Entity의 Id 추출
        List<Long> conversationIds = new ArrayList<>(); // 대화 id 추출 리스트
        for(Conversation conversation : conversationList) {
            conversationIds.add(conversation.getId());
        }

        // 대화들의 감정 추출
        List<Emotion> emotionList = emotionRepository.findByConversationIdIn(conversationIds);
        for(Emotion emotion : emotionList){
            anger = anger.add(emotion.getAnger());
            sad = sad.add(emotion.getSad());
            anxious = anxious.add(emotion.getAnxious());
            happy = happy.add(emotion.getHappy());
            neutral = neutral.add(emotion.getNeutral());
        }


        return EmotionResponse.builder()
                .anger(anger.divide(BigDecimal.valueOf(emotionList.size())))
                .sad(sad.divide(BigDecimal.valueOf(emotionList.size())))
                .anxious(anxious.divide(BigDecimal.valueOf(emotionList.size())))
                .happy(happy.divide(BigDecimal.valueOf(emotionList.size())))
                .neutral(neutral.divide(BigDecimal.valueOf(emotionList.size())))
                .build();
    }


    public void EmotionAnalyzeUsingHuggingFace(Conversation conversation) {
        String url = "https://api-inference.huggingface.co/models/SchuylerH/bert-multilingual-go-emtions";

        String inputJson = String.format("{\"inputs\": \"%s\"}", conversation.getResponse());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(inputJson, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            String responseBody = responseEntity.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            JsonNode resultsArray = jsonNode.get(0);

            SaveEmotion(resultsArray, conversation); // 감정 값 저장

        } catch (RestClientException | IOException e) {
            e.printStackTrace();
        }
    }


    public void SaveEmotion (JsonNode resultsArray, Conversation conversation){
        Double angry= Double.valueOf(0);
        Double sad= Double.valueOf(0);
        Double anxious= Double.valueOf(0);
        Double happy= Double.valueOf(0);
        Double neutral= Double.valueOf(0);

        for (JsonNode resultNode : resultsArray) {
            String label = resultNode.get("label").asText(); // 감정 이름
            Double score = resultNode.get("score").asDouble(); // 감정 점수

            switch (label) {

                // 분노
                case "annoyance":
                case "disapproval":
                case "anger":
                    angry += score;
                    break;

                //슬픔
                case "sadness":
                case "disappointed":
                case "grief":
                case "remorse":
                    sad += score;
                    break;

                //불안
                case "nervousness":
                case "disgust":
                case "confusion":
                case "fear":
                case "embarrassment":
                    anxious += score;
                    break;

                //기쁨
                case "joy":
                case "love":
                case "admiration":
                case "amusement":
                case "optimism":
                case "excitement":
                case "gratitude":
                case "pride":
                    happy += score;
                    break;

                //중립
                case "neutral":
                case "realization":
                case "approval":
                case "caring":
                case "desire":
                case "curiosity":
                case "relief":
                case "surprise":
                    neutral += score;
                    break;
            }
        }

        Emotion emotion = Emotion.builder()
                .conversation(conversation)
                .anger(BigDecimal.valueOf(angry*100))
                .sad(BigDecimal.valueOf(sad*100))
                .anxious(BigDecimal.valueOf(anxious*100))
                .happy(BigDecimal.valueOf(happy*100))
                .neutral(BigDecimal.valueOf(neutral*100))
                .build();

        emotionRepository.save(emotion);
    }
}
