package com.jpaboard.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaboard.domain.Answer;
import com.jpaboard.domain.AnswerRepository;
import com.jpaboard.domain.Question;
import com.jpaboard.domain.QuestionRepository;
import com.jpaboard.domain.Result;
import com.jpaboard.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;	
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
		if (!HttpSessionUtills.isLoginUser(session)) {
			return null;
		}
		
		User loginUser = HttpSessionUtills.getUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(loginUser, question, contents);
		question.addAnswerCount();
		questionRepository.save(question);
		return answerRepository.save(answer);		
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		if (!HttpSessionUtills.isLoginUser(session)) {
			return Result.fail("로그인을 해야 합니다.");
		}
		
		Answer answer = answerRepository.findOne(id);
		User loginUser = HttpSessionUtills.getUserFromSession(session);
		
		if (!answer.isSameWriter(loginUser)) {
			return Result.fail("자신의 글만 삭제 할 수 있습니다.");
		}
		
		answerRepository.delete(id);
		
		Question question = questionRepository.findOne(questionId);
		question.deleteAnswerCount();
		questionRepository.save(question);
		return Result.ok();
	}
}
