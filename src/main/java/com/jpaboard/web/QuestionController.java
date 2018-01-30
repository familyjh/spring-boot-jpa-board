package com.jpaboard.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpaboard.domain.Question;
import com.jpaboard.domain.QuestionRepository;
import com.jpaboard.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
    private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtills.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		return "/qna/form";
	}
	
	@RequestMapping("")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtills.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User sessionUser = HttpSessionUtills.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
		questionRepository.save(newQuestion);
				
		return "redirect:/";
	}
}
