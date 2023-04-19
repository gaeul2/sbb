package com.mysite.sbb;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class QuestionAndAnswerTests {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private AnswerRepository answerRepository;
    
    @Test
    void 질문데이터에_값넣기(){
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
    void 질문데이터에_조회하기(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(6, all.size());
        
        Question q = all.get(0);
        assertEquals("스프링이 무엇인가요", q.getSubject());
    }
    
    @Test
    void 질문데이터에_id값으로_찾기(){
        Optional<Question> q1 = this.questionRepository.findById(1);
        if (q1.isPresent()){
            Question q = q1.get();
            assertEquals("스프링이 무엇인가요", q.getSubject());
        }
    }
    
    @Test
    void 질문데이터_제목으로_찾기(){
        Question q = this.questionRepository.findBySubject("스프링이 무엇인가요");
        assertEquals(1, q.getId());
    }
    
    @Test
    void 질문데이터_제목과_내용으로_찾기(){
        Question q = this.questionRepository.findBySubjectAndContent("test2", "test2");
        assertEquals(2, q.getId());
    }
    
    @Test
    void 질문데이터_제목의_유사함으로_찾기(){
        List<Question> qList = this.questionRepository.findBySubjectLike("스%");
        Question q = qList.get(0);
        assertEquals(1, q.getId());
    }
    
    @Test
    void 질문데이터_수정하기(){
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent()); //값이 true인지 테스트한다.
        Question q = oq.get(); // 조회한 데이터를 가져와서
        q.setSubject("수정된 제목"); //제목을 수정하고
        this.questionRepository.save(q); //저장
    }
    
    @Test
    void 질문데이터_삭제하기(){
        assertEquals(2, this.questionRepository.count()); // 총 데이터 갯수 .count()
        Optional<Question> oq = this.questionRepository.findById(1); //id가 1인객체 찾아서
        assertTrue(oq.isPresent()); //있으면
        Question q = oq.get(); //객체로 꺼내서
        this.questionRepository.delete(q); //삭제요청
        assertEquals(1, this.questionRepository.count()); //삭제됐으면 1개겠지?
    }
    
    @Test
    void 답변데이터_생성하고_저장하기(){
        Optional<Question> oq = this.questionRepository.findById(2); //id가 2인 질문글을 가져와서
        assertTrue(oq.isPresent()); //존재하면
        Question q = oq.get(); //꺼내라
        
        Answer a = new Answer(); //답변객체를 생성하고
        a.setContent("test2에 대한 답변입니다."); //내용을 지정하고
        a.setQuestion(q); //답변에 대한 질문을 q로 지정
        a.setCreate_date(LocalDateTime.now()); //생성일은 지금날짜
        this.answerRepository.save(a);
    }
    
    @Test
    void 답변조회하고_답변이_참조하는_질문확인하기(){
       Optional<Answer> oa = this.answerRepository.findById(1);
       assertTrue(oa.isPresent());
       Answer a = oa.get();
       assertEquals(2, a.getQuestion().getId());
    }
    
    @Test
    @Transactional //테스트코드에서 db연결 끊어짐 방지.
    void 질문에_달린_답변찾기(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        
        List<Answer> aList = q.getAnswerList();
        
        assertEquals(1, aList.size());
        assertEquals("test2에 대한 답변입니다.", aList.get(0).getContent());
    }
}

