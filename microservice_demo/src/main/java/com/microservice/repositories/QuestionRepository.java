package com.microservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findAllByCategory(String category);
	
//	@Query(value = "SELECT * FROM question q WHERE q.category =:cat ORDER BY RANDOM() LIMIT :qno" , nativeQuery = true)
	@Query("SELECT q FROM Question q WHERE q.category = :cat ORDER BY FUNCTION('RAND') LIMIT :qno")
	List<Question> findRandomQuestionByCategory(String cat, int qno);

}
