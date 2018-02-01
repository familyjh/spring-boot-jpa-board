package com.jpaboard.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
				
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		try {
			Question question = questionRepository.findOne(id);
			hasPermission(session, question);
			model.addAttribute("question", question);
			return "/qna/updateForm";				
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());			
			return "/user/login";
		}
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		try {
			Question question = questionRepository.findOne(id);
			hasPermission(session, question);
			question.update(title, contents);
			questionRepository.save(question);
			return String.format("redirect:/questions/%d", id);			
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());			
			return "/user/login";
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {
		try {
			Question question = questionRepository.findOne(id);
			hasPermission(session, question);
			questionRepository.delete(id);
			return "redirect:/";		
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());			
			return "/user/login";
		}
	}
	
	private boolean hasPermission(HttpSession session, Question question) {
		if (!HttpSessionUtills.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다."); 
		}		
		
		User loginUser = HttpSessionUtills.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다."); 
		}
		
		return true;
	}	
}