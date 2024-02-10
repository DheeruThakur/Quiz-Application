package com.microservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entities.Question;
import com.microservice.entities.Quiz;
import com.microservice.entities.QuizDto;
import com.microservice.entities.Response;
import com.microservice.exceptions.ResourceNotFoundException;
import com.microservice.repositories.QuestionRepository;
import com.microservice.repositories.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionRepository questionRepository;

	public Quiz createQuiz(String cat , int qno , String title) {
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		List<Question> randomQuestionByCategory = questionRepository.findRandomQuestionByCategory(cat , qno);
		quiz.setQuestions(randomQuestionByCategory);
		return quizRepository.save(quiz);
	}
	
	public List<QuizDto> fetchQuiz(int id) {
		List<QuizDto> quizDto = new ArrayList<>();
		Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("quiz mili hi nahi"));
		List<QuizDto> collect = quiz.getQuestions().stream().map(q -> {
			return new QuizDto(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
		}).collect(Collectors.toList());
		return collect;
	}

	public int generateResult(int id, List<Response> response) {
		int score = 0;
		Quiz quiz = quizRepository.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int i=0;
		for(Response resp : response) {
			if(resp.getResponse().equals(questions.get(i).getRightAnswer()))
				score++;
			i++;
		}
		return score;
	}
}
