package com.jpaboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomController {
	@GetMapping("/helloworld")
	public String welcome(String userID, int userAge, Model model) {
		System.out.println("userID : " + userID + " userAge : " + userAge);
		model.addAttribute("userID", userID);
		model.addAttribute("userAge", userAge);
		return "welcome";	// return되는 welcome text가 templates/welcome의 이름과 같아야 함.
	}
}
