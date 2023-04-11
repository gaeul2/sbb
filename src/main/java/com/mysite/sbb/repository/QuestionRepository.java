package com.mysite.sbb.repository;

import com.mysite.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String Content);
    List<Question> findBySubjectLike(String subject);
}