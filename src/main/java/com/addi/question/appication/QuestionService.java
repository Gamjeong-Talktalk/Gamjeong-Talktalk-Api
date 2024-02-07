package com.addi.question.appication;

import com.addi.question.appication.dto.QuestionResponse;
import com.addi.question.domain.Questions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

	public QuestionResponse getQuestionService(){
		List<String> randomQuestions = Questions.getRandomQuestions(5);
		QuestionResponse question  = new QuestionResponse(randomQuestions);
		return question;
	}
}
