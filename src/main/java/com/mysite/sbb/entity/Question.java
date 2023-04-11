package com.mysite.sbb.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 200)
    private String subject;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private LocalDateTime create_date;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
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
    
    public List<Answer> getAnswerList() {
        return answerList;
    }
    
    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
