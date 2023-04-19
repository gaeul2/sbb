package com.mysite.sbb.answer.entity;

import com.mysite.sbb.question.entity.Question;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private LocalDateTime create_date;
    
    @ManyToOne
    private Question question;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public LocalDateTime getCreate_date() {
        return create_date;
    }
    
    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }
    
    public Question getQuestion() {
        return question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
}
