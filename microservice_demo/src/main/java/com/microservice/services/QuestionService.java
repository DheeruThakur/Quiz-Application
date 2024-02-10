package com.microservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entities.Question;
import com.microservice.exceptions.ResourceNotFoundException;
import com.microservice.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public Question createQuestion(Question question) {
		Question save = questionRepository.save(question);
		return save;
	}
	
	public List<Question> getAllQuestions(){
		List<Question> list = questionRepository.findAll();
		return list;
	}
	
	public List<Question> getByCategory(String category){
		List<Question> byCategory = questionRepository.findAllByCategory(category);
		return byCategory;
	}
	
	public Question updateQuestion(Question question , int id) {
		Question savedQuestion = questionRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User mila hi nahi"));
		savedQuestion.setOption1(question.getOption1());
		savedQuestion.setOption2(question.getOption2());
		savedQuestion.setOption3(question.getOption3());
		savedQuestion.setOption4(question.getOption4());
		savedQuestion.setQuestionTitle(question.getQuestionTitle());
		savedQuestion.setCategory(question.getCategory());
		savedQuestion.setDifficultyLevel(question.getDifficultyLevel());
		savedQuestion.setRightAnswer(question.getRightAnswer());
		
		Question save = questionRepository.save(savedQuestion);
		return save;
	}
	
	public void deleteQuestion(int id) {
		questionRepository.deleteById(id);
	}
}
