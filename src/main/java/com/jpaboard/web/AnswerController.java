package com.jpaboard.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpaboard.domain.Answer;
import com.jpaboard.domain.AnswerRepository;
import com.jpaboard.domain.Question;
import com.jpaboard.domain.QuestionRepository;
import com.jpaboard.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;	
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		if (!HttpSessionUtills.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User loginUser = HttpSessionUtills.getUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(loginUser, question, contents);
		answerRepository.save(answer);		
		
		return String.format("redirect:/questions/%d", questionId);
	}
}
