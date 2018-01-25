package com.jpaboard.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("userID : " + user);
		users.add(user);		
		//return "index";
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		// template에서 사용자목록을 사용 할 수 있도록 함. 
		model.addAttribute("users", users);
		return "list";
	}
}
