package com.mysite.sbb;

import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Test
    void 데이터에_값넣기(){
        Question q1 = new Question();
        q1.setSubject("스프링이 무엇인가요");
        q1.setContent("스프링에 대해 알고 싶습니다.");
        q1.setCreate_date(LocalDateTime.now());
        this.questionRepository.save(q1); //첫번째 질문 저장
        
        Question q2 = new Question();
        q2.setSubject("test2");
        q2.setContent("test2");
        q2.setCreate_date(LocalDateTime.now());
        this.questionRepository.save(q2); //첫번째 질문 저장
    }
    
    @Test
    void 데이터_조회하기(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(6, all.size());
        
        Question q = all.get(0);
        assertEquals("스프링이 무엇인가요", q.getSubject());
    }
    
    @Test
    void 데이터_id값으로_찾기(){
        Optional<Question> q1 = this.questionRepository.findById(1);
        if (q1.isPresent()){
            Question q = q1.get();
            assertEquals("스프링이 무엇인가요", q.getSubject());
        }
    }
    
    @Test
    void 데이터_제목으로_찾기(){
        Question q = this.questionRepository.findBySubject("스프링이 무엇인가요");
        assertEquals(1, q.getId());
    }
    
    @Test
    void 데이터_제목과_내용으로_찾기(){
        Question q = this.questionRepository.findBySubjectAndContent("test2", "test2");
        assertEquals(2, q.getId());
    }
    
    @Test
    void 데이터_제목의_유사함으로_찾기(){
        List<Question> qList = this.questionRepository.findBySubjectLike("스%");
        Question q = qList.get(0);
        assertEquals(1, q.getId());
    }
    
    @Test
    void 데이터_수정하기(){
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent()); //값이 true인지 테스트한다.
        Question q = oq.get(); // 조회한 데이터를 가져와서
        q.setSubject("수정된 제목"); //제목을 수정하고
        this.questionRepository.save(q); //저장
    }
    
    @Test
    void 데이터_삭제하기(){
        assertEquals(2, this.questionRepository.count()); // 총 데이터 갯수 .count()
        Optional<Question> oq = this.questionRepository.findById(1); //id가 1인객체 찾아서
        assertTrue(oq.isPresent()); //있으면
        Question q = oq.get(); //객체로 꺼내서
        this.questionRepository.delete(q); //삭제요청
        assertEquals(1, this.questionRepository.count()); //삭제됐으면 1개겠지?
    }
}
