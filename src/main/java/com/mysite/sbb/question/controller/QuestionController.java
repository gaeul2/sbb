package com.mysite.sbb.question.controller;

import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    
    // final로 지정한뒤 @RequiredArgsConstructor와 함께사용하면 final변수가 꼭 들어가는 생성자 알아서 만들어줌.
    private final QuestionRepository questionRepository;
    
    @GetMapping("/question/list")
    public String list(Model model){//Model객체는 자바클래스와 템블릿 간의 연결고리 역할을 함.
                                    // Model객체에 값을 담아두면 그 값을 템플릿에서 사용가능.
        List<Question> questionList = questionRepository.findAll();//질문목록을 모두 가져오고
        model.addAttribute("questionList", questionList); //모델에 담으면 템플릿에서 사용가능
        
        return "question/question_list";
    }
}
