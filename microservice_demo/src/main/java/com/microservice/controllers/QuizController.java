package com.microservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entities.Quiz;
import com.microservice.entities.QuizDto;
import com.microservice.entities.Response;
import com.microservice.services.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category , @RequestParam int nQuestion , @RequestParam String qTitle) {
		Quiz quiz = quizService.createQuiz(category, nQuestion, qTitle);
		return new ResponseEntity<Quiz>(quiz , HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<QuizDto>> getQuiz(@PathVariable int id) {
		List<QuizDto> fetchQuiz = quizService.fetchQuiz(id);
		return new ResponseEntity<>(fetchQuiz , HttpStatus.CREATED);
	}
	
	@PostMapping("/result/{id}")
	public ResponseEntity<Integer> createResult(@PathVariable int id , @RequestBody List<Response> response) {
		int result = quizService.generateResult(id, response);
		return new ResponseEntity<Integer>(result , HttpStatus.OK);
	}
	

}
