package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String Content);
    List<Question> findBySubjectLike(String subject);
}
